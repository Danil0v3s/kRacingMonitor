package ui.components.dashboards.ferrari

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import iracing.telemetry.TelemetryData
import ui.components.Cell
import ui.components.grid.GridPad
import ui.components.grid.GridPadCells
import ui.components.grid.GridPadScope
import kotlin.math.floor
import kotlin.math.roundToInt

@Composable
fun FerrariDash(telemetry: Map<String, TelemetryData>) {
    GridPad(
        cells = GridPadCells(rowCount = 5, columnCount = 6),
        modifier = Modifier.background(Color.Black)
    ) {
        FirstRow(telemetry)
        SecondRow(telemetry)
        ThirdRow(telemetry)
        FourthRow(telemetry)
        FifthRow(telemetry)
    }
}

private fun GridPadScope.FirstRow(telemetry: Map<String, TelemetryData>) {
    item(row = 0, column = 0) {
        Cell(content = "R")
    }
    item(row = 0, column = 1) {
        Cell(title = "REC", content = "0")
    }
    item(row = 0, column = 2) {
        val fuel = telemetry["FuelLevel"]?.value?.toFloat() ?: 0.0f
        Cell(title = "FUEL", content = String.format("%.1f", fuel))
    }
    item(row = 0, column = 3) {
        Cell(title = "FUEL/LAP")
    }
    item(row = 0, column = 4) {
        val speed = telemetry["Speed"]
        val speedMs = speed?.value?.toFloatOrNull() ?: 0f
        val speedKms = floor(speedMs * 3.6f).toInt()

        Cell(
            title = "SPEED",
            content = speedKms.toString()
        )
    }
    item(row = 0, column = 5) {
        Cell(title = "ABS")
    }
}

private fun GridPadScope.SecondRow(telemetry: Map<String, TelemetryData>) {
    item(row = 1, column = 0) {
        val pressure = telemetry["dpLFTireColdPress"]?.value?.toFloat()?.roundToInt()?.div(100f)
        Cell(title = "P FL", content = pressure?.toString())
    }
    item(row = 1, column = 1) {
        val pressure = telemetry["dpRFTireColdPress"]?.value?.toFloat()?.roundToInt()?.div(100f)
        Cell(title = "P FR", content = pressure?.toString())
    }
    item(row = 1, column = 2, rowSpan = 2, columnSpan = 2) {
        val gear = when (val gearValue = telemetry["Gear"]?.value) {
            "0" -> "N"
            "-1" -> "R"
            else -> gearValue
        }

        Cell(content = gear, fontSize = 128.sp, modifier = Modifier.background(Color.Red))
    }
    item(row = 1, column = 4) {
        val cl = telemetry["LFtempCL"]?.value?.toFloat() ?: 1f
        val cm = telemetry["LFtempCM"]?.value?.toFloat() ?: 1f
        val cr = telemetry["LFtempCR"]?.value?.toFloat() ?: 1f
        val temp = ((cl + cm + cr) / 3).toInt()
        Cell(title = "T FL", content = temp.toString())
    }
    item(row = 1, column = 5) {
        val cl = telemetry["RFtempCL"]?.value?.toFloat() ?: 1f
        val cm = telemetry["RFtempCM"]?.value?.toFloat() ?: 1f
        val cr = telemetry["RFtempCR"]?.value?.toFloat() ?: 1f
        val temp = ((cl + cm + cr) / 3).toInt()
        Cell(title = "T FR", content = temp.toString())
    }
}

private fun GridPadScope.ThirdRow(telemetry: Map<String, TelemetryData>) {
    item(row = 2, column = 0) {
        val pressure = telemetry["dpLRTireColdPress"]?.value?.toFloat()?.roundToInt()?.div(100f)
        Cell(title = "P RL", content = pressure?.toString())
    }
    item(row = 2, column = 1) {
        val pressure = telemetry["dpRRTireColdPress"]?.value?.toFloat()?.roundToInt()?.div(100f)
        Cell(title = "P RR", content = pressure?.toString())
    }
    item(row = 2, column = 4) {
        val cl = telemetry["LRtempCL"]?.value?.toFloat() ?: 1f
        val cm = telemetry["LRtempCM"]?.value?.toFloat() ?: 1f
        val cr = telemetry["LRtempCR"]?.value?.toFloat() ?: 1f
        val temp = ((cl + cm + cr) / 3).toInt()
        Cell(title = "T RL", content = temp.toString())
    }
    item(row = 2, column = 5) {
        val cl = telemetry["RRtempCL"]?.value?.toFloat() ?: 1f
        val cm = telemetry["RRtempCM"]?.value?.toFloat() ?: 1f
        val cr = telemetry["RRtempCR"]?.value?.toFloat() ?: 1f
        val temp = ((cl + cm + cr) / 3).toInt()
        Cell(title = "T RR", content = temp.toString())
    }
}

private fun GridPadScope.FourthRow(telemetry: Map<String, TelemetryData>) {
    item(row = 3, column = 0, columnSpan = 2) {
        val seconds = telemetry["LapLastLapTime"]?.value?.toFloat() ?: 0f

        val minutes = (seconds / 60).toInt()
        val lapTime = if (minutes > 0) {
            String.format("%d:%06.3f", minutes, seconds % 60f)
        } else {
            "00:00.00"
        }

        Cell(title = "LAP TIME", content = lapTime)
    }
    item(row = 3, column = 2, columnSpan = 2) {
        val diff = telemetry["LapDeltaToOptimalLap"]?.value?.toFloat() ?: 0f
        val minutes = (diff / 60).toInt()

        val backgroundColor = when {
            diff == 0.0f -> Color.White
            diff > 0f -> Color.Red
            diff < 0f -> Color.Green
            else -> Color.White
        }
        val textColor = when {
            diff == 0.0f -> Color.Black
            diff > 0f -> Color.White
            diff < 0f -> Color.White
            else -> Color.Black
        }
        val delta = if (minutes > 0) {
            String.format("%d:%06.3f", minutes, diff % 60f)
        } else {
            String.format("%06.3f", diff % 60f)
        }
        Cell(title = "DIFF", content = delta, textColor = textColor, backgroundColor = backgroundColor)
    }
    item(row = 3, column = 4, columnSpan = 2) {
        Cell(title = "PREDICTED")
    }
}

private fun GridPadScope.FifthRow(telemetry: Map<String, TelemetryData>) {
    item(row = 4, column = 0) {
        Cell(title = "MIX", content = "1")
    }
    item(row = 4, column = 1) {
        Cell(title = "PED", content = "1")
    }
    item(row = 4, column = 2) {
        val tc = telemetry["dcTractionControl"]?.value?.toFloat()?.roundToInt()
        Cell(title = "TC 1", content = tc.toString())
    }
    item(row = 4, column = 3) {
        val tc = telemetry["dcTractionControl"]?.value?.toFloat()?.roundToInt()
        Cell(title = "TC 2", content = tc.toString())
    }
    item(row = 4, column = 4) {
        val abs = telemetry["dcABS"]?.value?.toFloat()?.roundToInt()
        Cell(title = "ABS", content = abs.toString())
    }
    item(row = 4, column = 5) {
        val brakeBias = telemetry["dcBrakeBias"]?.value?.toFloat() ?: 0.0f
        Cell(title = "BAL", content = String.format("%.1f", brakeBias))
    }
}