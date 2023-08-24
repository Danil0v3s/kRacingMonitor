package mahm

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val header: Header,
    val entries: List<Entry>,
    val gpuEntries: List<GPUEntry>
)
