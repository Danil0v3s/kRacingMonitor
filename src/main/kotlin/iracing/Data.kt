package iracing

import iracing.yaml.SessionInfoData
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val header: Header,
    val sessionInfo: SessionInfoData,
    val gpuEntries: List<String>
)
