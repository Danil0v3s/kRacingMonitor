import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.app.App

fun main() = application {
    val icon = painterResource("imgs/logo.svg")
    val state = rememberWindowState().apply {
        size = DpSize(800.dp, 480.dp)
        placement = WindowPlacement.Fullscreen
    }

    Window(
        state = state,
        onCloseRequest = { this@application.exitApplication() },
        icon = icon,
        visible = true,
        title = "kMonitor",
        resizable = false,
        undecorated = true,
    ) {
        App()
    }
}
