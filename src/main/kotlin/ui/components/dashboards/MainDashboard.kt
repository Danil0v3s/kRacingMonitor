package ui.components.dashboards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import iracing.telemetry.TelemetryData
import ui.components.dashboards.ferrari.FerrariDash

enum class Dashboard {
    Ferrari
}

@Composable
fun MainDashboard(telemetry: Map<String, TelemetryData>) {

    var currentDash by remember { mutableStateOf(Dashboard.Ferrari.ordinal) }

    Box(modifier = Modifier.clickable {
        if (Dashboard.entries.size - 1 < currentDash) {
            currentDash += 1
        }
    }) {
        when (currentDash) {
            0 -> FerrariDash(telemetry)
        }
    }
}
