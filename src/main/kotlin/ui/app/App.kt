package ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import repository.GameDataRepository
import repository.LedsController
import ui.components.dashboards.MainDashboard

@Composable
fun App() = MaterialTheme {

    val sessionState = GameDataRepository.session.collectAsState(null)
    val telemetryState = GameDataRepository.telemetry.collectAsState(null)

    LaunchedEffect(sessionState.value) {
        sessionState.value?.DriverInfo?.also {

            LedsController.setRevOptions(
                it.DriverCarIdleRPM,
                it.DriverCarSLFirstRPM,
                it.DriverCarSLShiftRPM,
                it.DriverCarSLLastRPM,
                it.DriverCarSLBlinkRPM
            )
        }
    }

    LaunchedEffect(telemetryState.value) {
        telemetryState.value?.telemetry?.get("RPM")?.value?.let {
            LedsController.updateRevs(it.toFloatOrNull()?.toInt() ?: 0)
        }
    }

    if (telemetryState.value == null) {
        Box {}
    } else {
        MainDashboard(telemetryState.value!!.telemetry)
    }
}
