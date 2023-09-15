package repository

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.webSocket
import io.ktor.client.request.get
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import iracing.Data
import iracing.yaml.SessionInfoData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object GameDataRepository {

    val client = HttpClient(OkHttp) {
        install(WebSockets)
        install(JsonFeature)
    }

    val session = flow<SessionInfoData?> {
        while (true) {
            try {
                val response: SessionInfoData = client.get("http://192.168.1.208:8080/session") {
                    url {
                        headers.append("Authorization", "Basic cm9vdDpwYXNz")
                    }
                }
                emit(response)
                delay(30_000L)
            } catch (e: Exception) {
                emit(null)
            }
        }
    }.distinctUntilChanged()

    val telemetry = flow<Data?> {
        client.webSocket(
            method = HttpMethod.Get,
            host = "192.168.1.208",
            port = 8080,
            path = "/telemetry",
            request = {
                headers.append("Authorization", "Basic cm9vdDpwYXNz")
            }
        ) {
            while (true) {
                try {
                    val othersMessage = incoming.receive() as? Frame.Text ?: continue
                    emit(Json.decodeFromString<Data>(othersMessage.readText()))
                } catch (e: Exception) {
                    emit(null)
                }
            }
        }
    }
}
