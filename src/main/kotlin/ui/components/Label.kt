import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

@Composable
fun Label(
    text: String,
    style: TextStyle = MaterialTheme.typography.subtitle2
) = Text(
    text = text,
    style = style
)
