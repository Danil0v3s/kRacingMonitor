package ui

import Label
import Title
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import repository.PreferencesRepository
import win32.WinRegistry

const val PREFERENCE_AUTO_START_SERVER = "PREFERENCE_AUTO_START_SERVER"
const val PREFERENCE_START_MINIMIZED = "PREFERENCE_START_MINIMIZED"

@Composable
fun SettingsUi() = Box(
    modifier = Modifier
        .padding(16.dp)
) {
    Column {
        Title(text = "App settings")
        startWithWindowsCheckbox()
        startMinimizedCheckbox()
        autoStartServerCheckbox()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun startWithWindowsCheckbox() {
    var state by remember { mutableStateOf(WinRegistry.isAppRegisteredToStartWithWindows()) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = { value ->
                state = value
                if (value) {
                    WinRegistry.registerAppToStartWithWindows()
                } else {
                    WinRegistry.removeAppFromStartWithWindows()
                }
            },
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
        )

        Label(text = "Start with Windows")

        TooltipArea({
            Label(text = "Admin rights needed")
        }) {
            Icon(imageVector = Icons.Filled.AdminPanelSettings, null)
        }
    }
}

@Composable
private fun startMinimizedCheckbox() {
    var state by remember { mutableStateOf(PreferencesRepository.getPreferenceBoolean(PREFERENCE_START_MINIMIZED)) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = { value ->
                state = value
                PreferencesRepository.setPreferenceBoolean(PREFERENCE_START_MINIMIZED, value)
            },
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
        )

        Label(text = "Start minimized")
    }
}

@Composable
private fun autoStartServerCheckbox() {
    var state by remember { mutableStateOf(PreferencesRepository.getPreferenceBoolean(PREFERENCE_AUTO_START_SERVER)) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = state,
            onCheckedChange = { value ->
                state = value
                PreferencesRepository.setPreferenceBoolean(PREFERENCE_AUTO_START_SERVER, value)
            },
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
        )

        Label(text = "Auto start server")
    }
}
