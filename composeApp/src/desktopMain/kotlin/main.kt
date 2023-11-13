import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.awt.Dimension

fun main() = application {
    Window(
        title = "Messager",
        onCloseRequest = ::exitApplication,
    ) {
        LaunchedEffect(Unit) {
            window.minimumSize = Dimension(360, 420)
        }
        App()
    }
}
