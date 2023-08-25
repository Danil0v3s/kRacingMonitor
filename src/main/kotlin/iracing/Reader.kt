@file:OptIn(ExperimentalStdlibApi::class)

package iracing

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT
import iracing.MAHMSizes.IRSDK_MAX_DESC
import iracing.MAHMSizes.IRSDK_MAX_STRING
import iracing.telemetry.TelemetryData
import iracing.yaml.SessionInfoData
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import win32.WindowsService
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.charset.StandardCharsets

object MAHMSizes {
    const val IRSDK_MAX_BUFS = 4
    const val IRSDK_MAX_STRING = 32

    // descriptions can be longer than max_string!
    const val IRSDK_MAX_DESC = 64

    const val IRSDK_UNLIMITED_LAPS = 32767
    const val IRSDK_UNLIMITED_TIME = 604800.0f

    // latest version of our telemetry headers
    const val IRSDK_VER = 2
}

private const val YML_MAP_FILE_NAME = "Local\\IRSDKMemMapFileName"
private const val EVENTS_MAP_FILE_NAME = "Local\\IRSDKDataValidEvent"

class Reader {

    private val windowsService = WindowsService()
    private var pollingJob: Job? = null

    private var yamlMemoryMapFile: WinNT.HANDLE? = null
    private var yamlPointer: Pointer? = null

    private var eventsMemoryMapFile: WinNT.HANDLE? = null
    private var eventsPointer: Pointer? = null

    private val yamlParser by lazy {
        ObjectMapper(YAMLFactory()).apply {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            registerModule(KotlinModule())
        }
    }

    var pollingInterval = 200L
    val currentData = flow<Data?> {
        tryOpenMemoryFile()

        yamlPointer ?: return@flow

        while (true) {
            try {
                emit(readData(yamlPointer!!))
                delay(pollingInterval)
            } catch (e: CancellationException) {
                break
            }
        }
    }

    fun stopPolling() {
        pollingJob?.cancel()
        pollingJob = null
        yamlPointer?.let { windowsService.unmapViewOfFile(it) }
        yamlMemoryMapFile?.let { windowsService.closeHandle(it) }
    }

    fun tryOpenMemoryFile() {
        windowsService.openMemoryMapFile(YML_MAP_FILE_NAME)?.let { handle ->
            yamlMemoryMapFile = handle
            yamlPointer = windowsService.mapViewOfFile(handle) ?: throw Error("Could not create pointer")
        } ?: throw Error("Could not read MAHMSharedMemory")
    }

    fun tryOpenEventMemoryFile() {
        windowsService.openEventFile(EVENTS_MAP_FILE_NAME)?.let { handle ->
            eventsMemoryMapFile = handle
        } ?: throw Error("Could not read MAHMSharedMemory")
    }

    private fun readHeader(pointer: Pointer): Header {
        val buffer = getByteBuffer(pointer, Header.HEADER_SIZE)

        return readHeader(buffer)
    }

    private fun readData(pointer: Pointer): Data {
        val header = readHeader(pointer)
        val latestPointerBuffer = header.getLatestVarByteBuffer(pointer)
        val telemetryData = mutableMapOf<String, TelemetryData>()

        for (i in 0 until header.numVars) {
            val buffer = pointer.getByteArray(header.varHeaderOffset + (144L * i), 144).let {
                ByteBuffer.wrap(it).order(
                    ByteOrder.LITTLE_ENDIAN
                )
            }

            val type = buffer.int
            val offset = buffer.int
            val count = buffer.int

            var localBuffer = ByteArray(IRSDK_MAX_STRING)
            buffer.position(16)
            buffer[localBuffer, 0, localBuffer.size]
            val name = String(localBuffer).replace("[\u0000]".toRegex(), "")

            buffer.position(16 + IRSDK_MAX_STRING + IRSDK_MAX_DESC)
            buffer[localBuffer, 0, localBuffer.size]
            val unit = String(localBuffer).replace("[\u0000]".toRegex(), "")

            localBuffer = ByteArray(IRSDK_MAX_DESC)
            buffer.position(16 + IRSDK_MAX_STRING)
            buffer[localBuffer, 0, localBuffer.size]
            val desc = String(localBuffer).replace("[\u0000]".toRegex(), "")

            val value = when (type) {
                // char, bool
                0, 1 -> latestPointerBuffer.getChar(offset).toString().toBoolean()
                2, 3 -> latestPointerBuffer.getInt(offset)
                4 -> latestPointerBuffer.getFloat(offset)
                5 -> latestPointerBuffer.getDouble(offset)
                else -> -1
            }.toString()

            telemetryData[name] = TelemetryData(
                description = desc,
                value = value,
                unit = unit
            )
        }

        return Data(
            telemetry = telemetryData
        )
    }

    private fun readSessionInfoData(buffer: ByteBuffer, header: Header): SessionInfoData {
        val yml = buffer.readString(buffer.remaining())
        return yamlParser.readValue(yml, SessionInfoData::class.java)
    }

    private fun getByteBuffer(pointer: Pointer, size: Int, skip: Int = 0): ByteBuffer {
        val buffer = ByteBuffer.allocateDirect(size)
        buffer.put(pointer.getByteArray(skip * 1L, size))
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        buffer.rewind()
        // buffer.position(skip)

        return buffer
    }

    private fun readHeader(buffer: ByteBuffer) = Header(
        version = buffer.int,
        status = buffer.int,
        tickRate = buffer.int,
        sessionInfoUpdate = buffer.int,
        sessionInfoLen = buffer.int,
        sessionInfoOffset = buffer.int,
        numVars = buffer.int,
        varHeaderOffset = buffer.int,
        numBuf = buffer.int,
        bufLen = buffer.int
    )

    private fun ByteBuffer.readString(size: Int): String {
        val array = ByteArray(size)
        get(array, 0, size)

        return String(trim(array), StandardCharsets.UTF_8)
    }

    private fun trim(bytes: ByteArray): ByteArray {
        var i = bytes.size - 1
        while (i >= 0 && bytes[i].toInt() == 0) {
            --i
        }
        return bytes.copyOf(i + 1)
    }
}
