package ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import repository.GameDataRepository
import com.diozero.ws281xj.PixelAnimations
import com.diozero.ws281xj.rpiws281x.WS281x
import ui.components.dashboards.MainDashboard

@Composable
fun App() = MaterialTheme {

//    val sessionState = GameDataRepository.session.collectAsState(null)
    val telemetryState = GameDataRepository.telemetry.collectAsState(null)

//    LaunchedEffect(Unit) {
//        val driver = WS281x(18, 125, 18)
//        PixelAnimations.demo(driver)
//    }

    if (telemetryState.value == null) {
        Box {}
    } else {
        MainDashboard(telemetryState.value!!.telemetry)
    }
}
