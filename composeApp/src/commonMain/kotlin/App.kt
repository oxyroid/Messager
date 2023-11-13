import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import feature.mail.MailRoute
import ui.*
import ui.components.UncaughtExceptionHandler
import ui.scaffolds.HomeScaffold
import ui.toolbars.HomeToolbar
import ui.toolbars.HomeToolbarDestination

@Composable
fun App(
    modifier: Modifier = Modifier
) {
    BasicApp {
        var destination: HomeToolbarDestination by remember { mutableStateOf(HomeToolbarDestination.MAILS) }
        HomeScaffold(
            toolbar = {
                HomeToolbar(destination = destination) { destination = it }
            },
            modifier = modifier
        ) {
            when (destination) {
                HomeToolbarDestination.MAILS -> MailRoute()
                HomeToolbarDestination.SETTINGS -> {}
            }
        }
    }
}

@Composable
private fun BasicApp(
    content: @Composable () -> Unit
) {
    var exception: Exception? by remember { mutableStateOf(null) }

    val sentry = remember {
        object : Sentry {
            override fun note(message: String) {
                // show note
            }
            override fun catch(e: Exception) {
                exception = e
            }
        }
    }
    CompositionLocalProvider(
        LocalTheme provides DayTheme,
        LocalSpacing provides Spacing(),
        LocalSentry provides sentry
    ) {
        val theme = LocalTheme.current
        MaterialTheme(
            colors = MaterialTheme.colors.copy(
                primary = theme.primary,
                onPrimary = theme.onPrimary,
                background = theme.background,
                onBackground = theme.background,
                surface = theme.surface,
                onSurface = theme.onSurface
            )
        ) {
            Box {
                content()
                UncaughtExceptionHandler(
                    exception = exception,
                    onDismiss = { exception = null }
                )
            }
        }
    }
}

@Stable
interface Sentry {
    fun note(message: String)
    fun catch(e: Exception)
}

val LocalSentry = staticCompositionLocalOf<Sentry> { providableCompostionLocalNotFound() }