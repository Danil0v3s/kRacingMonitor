package ui

import Label
import Title
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.ktor.application.ApplicationStarted
import io.ktor.application.ApplicationStopped
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.basic
import io.ktor.http.cio.websocket.send
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngineEnvironment
import io.ktor.server.engine.EngineConnectorConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mahm.Reader
import mahm.SourceID
import mahm.dto.toDTO
import repository.PreferencesRepository

data class ServerState(
    val isRunning: Boolean = false,
    val port: String = "8080",
    val user: String = "root",
    val pass: String = "pass",
    val pollingRate: Long = 200L
)

sealed class ServerEvent {
    data class Started(val connector: EngineConnectorConfig) : ServerEvent()
    object Stopped : ServerEvent()
}

@Composable
fun ServerUi(
    reader: Reader
) {
    var state by remember { mutableStateOf(ServerState()) }
    fun updateState(serverEvent: ServerEvent) {
        state = when (serverEvent) {
            is ServerEvent.Started -> {
                state.copy(isRunning = true)
            }

            is ServerEvent.Stopped -> {
                state.copy(isRunning = false)
            }
        }
    }

    var server by remember {
        mutableStateOf(
            createServer(
                reader = reader,
                options = state,
                onServerEvent = ::updateState
            )
        )
    }
    val pollingRateOptions = listOf("200", "300", "400", "500", "600")

    LaunchedEffect(Unit) {
        if (PreferencesRepository.getPreferenceBoolean(PREFERENCE_AUTO_START_SERVER, false)) {
            server.start(wait = false)
        }
    }

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row {
            Column {
                Title(text = "Server configuration")

                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        maxLines = 1,
                        modifier = Modifier.width(120.dp),
                        value = state.port,
                        onValueChange = { state = state.copy(port = it) },
                        label = { Text("Port") }
                    )

                    DropdownMenu(
                        options = pollingRateOptions,
                        onValueChanged = { state = state.copy(pollingRate = it.toLong()) }
                    )
                }

                Row(
                    modifier = Modifier.padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        maxLines = 1,
                        modifier = Modifier.width(120.dp),
                        value = state.user,
                        onValueChange = { state = state.copy(user = it) },
                        label = { Text("User") }
                    )

                    OutlinedTextField(
                        maxLines = 1,
                        modifier = Modifier.width(120.dp),
                        value = state.pass,
                        onValueChange = { state = state.copy(pass = it) },
                        label = { Text("Password") }
                    )
                }

                serverButtonRows(
                    state = state,
                    onStopServerClicked = {
                        if (state.isRunning) {
                            server.stop(1000L, 3000L)
                        }
                    },
                    onStartServerClicked = {
                        if (!state.isRunning) {
                            server = createServer(
                                reader = reader,
                                options = state,
                                onServerEvent = ::updateState
                            )
                            server.start(wait = false)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun serverButtonRows(
    state: ServerState,
    onStopServerClicked: () -> Unit,
    onStartServerClicked: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Label(text = "ws://localhost:${state.port}")
        }

        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary, shape = MaterialTheme.shapes.small)
                .height(36.dp)
                .padding(end = 12.dp, start = 6.dp)
                .clickable {
                    if (state.isRunning) {
                        onStopServerClicked()
                    } else {
                        onStartServerClicked()
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val icon: ImageVector
            val label: String
            if (state.isRunning) {
                icon = Icons.Filled.Stop
                label = "Stop Server"
            } else {
                icon = Icons.Filled.PlayArrow
                label = "Start Server"
            }
            Icon(
                imageVector = icon, null, tint = Color.White
            )
            Label(text = label, style = MaterialTheme.typography.subtitle2.copy(color = Color.White))
        }
    }
}

@Composable
fun DropdownMenu(
    options: List<String>,
    onValueChanged: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options.first()) }

    Box(
        modifier = Modifier.width(120.dp)
    ) {
        OutlinedTextField(
            maxLines = 1,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterEnd),
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text("Poll Rate (ms)") }
        )
        Icon(
            modifier = Modifier.align(Alignment.CenterEnd).clickable { expanded = !expanded },
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = ""
        )

        if (expanded) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().background(Color.White)
            ) {
                items(options) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(4.dp).clickable {
                            selectedOption = it
                            onValueChanged(it)
                            expanded = !expanded
                        },
                        text = it
                    )
                }
            }
        }
    }
}

private fun createServer(
    reader: Reader,
    options: ServerState,
    onServerEvent: (ServerEvent) -> Unit
): NettyApplicationEngine {
    return embeddedServer(Netty, port = options.port.toInt()) {
        install(WebSockets)
        install(Authentication) {
            basic("auth-basic") {
                validate { credentials ->
                    if (credentials.name == options.user && credentials.password == options.pass) {
                        UserIdPrincipal(credentials.name)
                    } else {
                        null
                    }
                }
            }
        }

        routing {
            authenticate("auth-basic") {
                webSocket("/socket") {
                    reader.currentData.filterNotNull().collect {
                        val queryParams = call.request.queryParameters
                        val filters = queryParams["filter"]?.split(",").orEmpty().map { SourceID.fromString(it) }
                        val shouldUseDto = queryParams["slim"]?.toBoolean() ?: false

                        val result = when {
                            filters.isNotEmpty() && shouldUseDto -> {
                                val result = it.copy(
                                    entries = it.entries.filter {
                                        filters.contains(it.dwSrcId)
                                    }
                                )
                                Json.encodeToString(result.toDTO())
                            }

                            filters.isNotEmpty() -> {
                                val result = it.copy(
                                    entries = it.entries.filter {
                                        filters.contains(it.dwSrcId)
                                    }
                                )
                                Json.encodeToString(result)
                            }

                            shouldUseDto -> Json.encodeToString(it.toDTO())
                            else -> Json.encodeToString(it)
                        }

                        send(result)
                    }
                }
            }
        }

        environment.monitor.apply {
            subscribe(ApplicationStarted) {
                reader.pollingInterval = options.pollingRate.toLong()
                reader.tryOpenMemoryFile()
                onServerEvent(ServerEvent.Started((environment as ApplicationEngineEnvironment).connectors.first()))
            }
            subscribe(ApplicationStopped) {
                onServerEvent(ServerEvent.Stopped)
                reader.stopPolling()
            }
        }
    }
}
