package ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import iracing.Data
import repository.GameDataRepository
import ui.components.Cell
import ui.components.grid.GridPad
import ui.components.grid.GridPadCells
import ui.components.grid.GridPadScope
import kotlin.math.floor
import kotlin.math.roundToInt

@Composable
fun App() = MaterialTheme {

//    val sessionState = GameDataRepository.session.collectAsState(null)
    val telemetry = GameDataRepository.telemetry.collectAsState(null)

//    LaunchedEffect(Unit) {
//        val driver = WS281x(18, 125, 18)
//        PixelAnimations.demo(driver)
//    }

    if (telemetry.value == null) {
        Box {}
    } else {
        GridPad(
            cells = GridPadCells(rowCount = 5, columnCount = 6),
            modifier = Modifier.background(Color.Black)
        ) {
            FirstRow(telemetry.value!!)
            SecondRow(telemetry.value!!)
            ThirdRow(telemetry.value!!)
            FourthRow(telemetry.value!!)
            FifthRow(telemetry.value!!)
        }
    }
}

private fun GridPadScope.FifthRow(telemetry: Data) {
    item(row = 4, column = 0) {
        Cell(title = "MIX")
    }
    item(row = 4, column = 1) {
        Cell(title = "PED")
    }
    item(row = 4, column = 2) {
        val tc = telemetry.telemetry["dcTractionControl"]?.value?.toFloat()?.roundToInt()
        Cell(title = "TC 1", content = tc.toString())
    }
    item(row = 4, column = 3) {
        val tc = telemetry.telemetry["dcTractionControl"]?.value?.toFloat()?.roundToInt()
        Cell(title = "TC 2", content = tc.toString())
    }
    item(row = 4, column = 4) {
        val abs = telemetry.telemetry["dcABS"]?.value?.toFloat()?.roundToInt()
        Cell(title = "ABS", content = abs.toString())
    }
    item(row = 4, column = 5) {
        val brakeBias = telemetry.telemetry["dcBrakeBias"]?.value?.toFloat() ?: 0
        Cell(title = "BAL", content = String.format("%.1f", brakeBias))
    }
}

private fun GridPadScope.FourthRow(telemetry: Data) {
    item(row = 3, column = 0, columnSpan = 2) {
        val seconds = telemetry.telemetry["LapLastLapTime"]?.value?.toFloat() ?: 0f

        val minutes = (seconds / 60).toInt()
        val lapTime = if (minutes > 0) {
            String.format("%d:%06.3f", minutes, seconds % 60f)
        } else {
            "00:00.00"
        }

        Cell(title = "LAP TIME", content = lapTime)
    }
    item(row = 3, column = 2, columnSpan = 2) {
        val diff = telemetry.telemetry["LapDeltaToOptimalLap"]?.value?.toFloat() ?: 0f
        val minutes = (diff / 60).toInt()

        val textColor =  when {
            diff == 0.0f -> Color.White
            diff > 0f -> Color.Red
            diff < 0f -> Color.Green
            else -> Color.White
        }
        val delta = if (minutes > 0) {
            String.format("%d:%06.3f", minutes, diff % 60f)
        } else {
            String.format("%06.3f", diff % 60f)
        }
        Cell(title = "DIFF", content = delta, textColor = textColor)
    }
    item(row = 3, column = 4, columnSpan = 2) {
        Cell(title = "PREDICTED")
    }
}

private fun GridPadScope.ThirdRow(telemetry: Data) {
    item(row = 2, column = 0) {
        Cell(title = "P RL")
    }
    item(row = 2, column = 1) {
        Cell(title = "P RR")
    }
    item(row = 2, column = 4) {
        Cell(title = "ROAD T")
    }
    item(row = 2, column = 5) {
        Cell(title = "LAPS F")
    }
}

private fun GridPadScope.SecondRow(telemetry: Data) {
    item(row = 1, column = 0) {
        Cell(title = "P FL")
    }
    item(row = 1, column = 1) {
        Cell(title = "P FR")
    }
    item(row = 1, column = 2, rowSpan = 2, columnSpan = 2) {
        val gear = when (val gearValue = telemetry.telemetry["Gear"]?.value) {
            "0" -> "N"
            "-1" -> "R"
            else -> gearValue
        }

        Cell(content = gear, fontSize = 128.sp, modifier = Modifier.background(Color.Red))
    }
    item(row = 1, column = 4) {
        Cell(title = "OIL")
    }
    item(row = 1, column = 5) {
        Cell(title = "WATER")
    }
}

private fun GridPadScope.FirstRow(telemetry: Data) {
    item(row = 0, column = 0) {
        Cell(content = "R")
    }
    item(row = 0, column = 1) {
        Cell(title = "REC")
    }
    item(row = 0, column = 2) {
        val fuel = telemetry.telemetry["FuelLevel"]?.value?.toFloat() ?: 0.0f
        Cell(title = "FUEL", content = String.format("%.1f", fuel))
    }
    item(row = 0, column = 3) {
        Cell(title = "FUEL/LAP")
    }
    item(row = 0, column = 4) {
        val speed = telemetry.telemetry["Speed"]
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
