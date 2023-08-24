package iracing

import com.sun.jna.Pointer
import kotlinx.serialization.Serializable
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Serializable
data class Header(
    val version: Int,
    val status: Int,
    val tickRate: Int,
    val sessionInfoUpdate: Int,
    val sessionInfoLen: Int,
    val sessionInfoOffset: Int,
    val numVars: Int,
    val varHeaderOffset: Int,
    val numBuf: Int,
    val bufLen: Int,
) {
    companion object {
        const val HEADER_SIZE = 112
        const val VARBUF_SIZE = 4 * 4
    }

    val totalSize: Int
        get() = 0

    fun getVarBufTickCount(varBuf: Int, sharedMemory: Pointer): Int = sharedMemory.getInt((varBuf * VARBUF_SIZE) + 48L)

    fun getVarBufBufOffset(varBuf: Int, sharedMemory: Pointer): Int = sharedMemory.getInt((varBuf * VARBUF_SIZE) + 52L)

    fun getLatestVarByteBuffer(sharedMemory: Pointer): ByteBuffer {
        return ByteBuffer.wrap(
            sharedMemory.getByteArray(
                getVarBufBufOffset(getLatestVarBuffIdx(sharedMemory), sharedMemory) * 1L,
                bufLen
            )
        ).apply {
            order(ByteOrder.LITTLE_ENDIAN)
        }
    }

    private fun getLatestVarBuffIdx(sharedMemory: Pointer): Int {
        var latest = 0
        for (i in 1 until numBuf) {
            if (getVarBufTickCount(latest, sharedMemory) < getVarBufTickCount(i, sharedMemory)) {
                latest = i
            }
        }
        return latest
    }
}
