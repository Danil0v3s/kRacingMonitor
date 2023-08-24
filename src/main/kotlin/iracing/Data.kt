package iracing

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val header: Header,
    val entries: List<String>,
    val gpuEntries: List<String>
)
