package ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Cell(
    title: String? = null,
    content: String? = null,
    fontSize: TextUnit = 24.sp,
    textColor: Color = Color.White,
    backgroundColor: Color = Color.Black,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(4.dp)
    ) {

        Box(
            modifier = modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .border(BorderStroke(2.dp, if (backgroundColor == Color.White) Color.Black else Color.White))
        )

        if (title != null) {
            Text(
                text = title,
                color = if (backgroundColor == Color.White) Color.Black else Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(450),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .background(backgroundColor)
                    .padding(horizontal = 6.dp)
                    .offset(y = (-4).dp)
            )
        }

        if (content != null) {
            Text(
                text = content,
                color = textColor,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(800),
                fontSize = fontSize,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}