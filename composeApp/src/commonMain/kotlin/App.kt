import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import data.wrapper.Castable
import feature.mail.MailRoute
import feature.settings.SettingsRoute
import ui.*
import ui.components.UncaughtExceptionHandler
import ui.scaffolds.HomeScaffold
import ui.toolbars.HomeToolbar
import ui.toolbars.HomeDestination

@Composable
fun App(
    modifier: Modifier = Modifier
) {
    BasicApp {
        var destination: HomeDestination by remember { mutableStateOf(HomeDestination.MAILS) }
        HomeScaffold(
            toolbar = {
                HomeToolbar(destination) { destination = it }
            },
            modifier = modifier
        ) {
            when (destination) {
                HomeDestination.MAILS -> MailRoute()
                HomeDestination.SETTINGS -> SettingsRoute()
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
            override fun cast(castable: Castable) {
                exception = castable.cast()
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
    fun cast(castable: Castable)
    fun catch(e: Exception)
}

val LocalSentry = staticCompositionLocalOf<Sentry> { providableCompostionLocalNotFound() }