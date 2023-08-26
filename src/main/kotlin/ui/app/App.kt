package ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.diozero.ws281xj.PixelAnimations
import com.diozero.ws281xj.rpiws281x.WS281x
import repository.GameDataRepository

@Composable
fun App() = MaterialTheme {

    val sessionState = GameDataRepository.session.collectAsState(null)
    val telemetry = GameDataRepository.telemetry.collectAsState(null)

    LaunchedEffect(Unit) {
        val driver = WS281x(18, 125, 18)
        PixelAnimations.demo(driver)
    }

    if (telemetry.value == null) {
        Box {}
    } else {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column {
                Text(text = telemetry.value!!.telemetry["Speed"]!!.value)
                Text(text = telemetry.value!!.telemetry["Gear"]!!.value)
                Text(text = telemetry.value!!.telemetry["RPM"]!!.value)
            }
        }
    }
}
