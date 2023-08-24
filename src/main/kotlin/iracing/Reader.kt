@file:OptIn(ExperimentalStdlibApi::class)

package iracing

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinNT
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

private const val MEMORY_MAP_FILE_NAME = "Local\\IRSDKMemMapFileName"
private const val IRSDK_DATAVALIDEVENTNAME = "Local\\IRSDKDataValidEvent"

class Reader {

    private val windowsService = WindowsService()
    private var pollingJob: Job? = null

    private var memoryMapFile: WinNT.HANDLE? = null
    private var pointer: Pointer? = null

    private val yamlParser by lazy {
        ObjectMapper(YAMLFactory()).apply {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            registerModule(KotlinModule())
        }
    }

    var pollingInterval = 200L
    val currentData = flow<Data?> {
        tryOpenMemoryFile()
        pointer ?: return@flow

        while (true) {
            try {
                emit(readData(pointer!!))
                delay(pollingInterval)
            } catch (e: CancellationException) {
                break
            }
        }
    }

    fun stopPolling() {
        pollingJob?.cancel()
        pollingJob = null
        pointer?.let { windowsService.unmapViewOfFile(it) }
        memoryMapFile?.let { windowsService.closeHandle(it) }
    }

    fun tryOpenMemoryFile() {
        windowsService.openMemoryMapFile(MEMORY_MAP_FILE_NAME)?.let { handle ->
            memoryMapFile = handle
            pointer = windowsService.mapViewOfFile(handle) ?: throw Error("Could not create pointer")
        } ?: throw Error("Could not read MAHMSharedMemory")
    }

    private fun readHeader(pointer: Pointer): Header {
        val buffer = getByteBuffer(pointer, Header.HEADER_SIZE)

        return readHeader(buffer)
    }

    private fun readData(pointer: Pointer): Data {
        val header = readHeader(pointer)

        val buffer = getByteBuffer(pointer, header.varHeaderOffset, Header.HEADER_SIZE)
        readVars(buffer, header)

        return Data(
            header = header,
            entries = emptyList(),
            gpuEntries = emptyList()
        )
    }

    var write = true

    private fun readVars(buffer: ByteBuffer, header: Header) {
        val yml = buffer.readString(buffer.remaining())
        val result = yamlParser.readValue(yml, SessionInfoData::class.java)
    }

    private fun getByteBuffer(pointer: Pointer, size: Int, skip: Int = 0): ByteBuffer {
        val buffer = ByteBuffer.allocateDirect(size)
        buffer.put(pointer.getByteArray(0, size))
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        buffer.rewind()
        buffer.position(skip)

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
