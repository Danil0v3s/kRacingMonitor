import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import repository.PreferencesRepository
import ui.PREFERENCE_START_MINIMIZED
import ui.app.App

fun main() = application {
    val icon = painterResource("imgs/logo.svg")
    val state = rememberWindowState().apply {
        size = DpSize(300.dp, 530.dp)
    }
    var isVisible by remember { mutableStateOf(PreferencesRepository.getPreferenceBooleanNullable(PREFERENCE_START_MINIMIZED)?.not() ?: true) }

    Window(
        state = state,
        onCloseRequest = { isVisible = false },
        icon = icon,
        visible = isVisible,
        title = "kMonitor",
        resizable = false
    ) {
        App()
    }

    if (!isVisible) {
        Tray(
            icon = icon,
            onAction = { isVisible = true },
            menu = {
                Item("Quit", onClick = ::exitApplication)
            }
        )
    }
}
