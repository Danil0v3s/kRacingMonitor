package ui.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import iracing.Reader
import ui.FooterUi
import ui.ServerUi
import ui.SettingsUi

@Composable
fun App(
    reader: Reader = Reader()
) = MaterialTheme {
    Row(modifier = Modifier.fillMaxSize()) {
        Column {
            ServerUi(reader)
            Divider()
            SettingsUi()
            FooterUi()
        }
    }
}
