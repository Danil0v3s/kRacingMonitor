package iracing

import iracing.telemetry.TelemetryData
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val telemetry: Map<String, TelemetryData>,
)
