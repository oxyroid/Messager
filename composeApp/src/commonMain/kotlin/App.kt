import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import feature.mail.list.ListRoute
import kotlinx.coroutines.delay
import ui.*
import ui.components.UncaughtExceptionHandler
import ui.scaffolds.HomeScaffold
import ui.scaffolds.MailScaffold
import ui.toolbars.HomeToolbar
import ui.toolbars.HomeToolbarDestination
import kotlin.time.Duration.Companion.seconds

@Composable
fun App() {
    BasicApp {
        val catcher = LocalCatcher.current
        var destination: HomeToolbarDestination by remember { mutableStateOf(HomeToolbarDestination.MAILS) }
        HomeScaffold(
            toolbar = {
                HomeToolbar(destination = destination) { destination = it }
            }
        ) {
            when (destination) {
                HomeToolbarDestination.MAILS -> {
                    MailScaffold(
                        list = {
                            ListRoute()
                        },
                        detail = @Composable {
                            LaunchedEffect(Unit) {
                                delay(3.seconds)
                                catcher.catch(RuntimeException("Hello!"))
                            }
                        }
                    )
                }

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

    val catcher = remember {
        object : Catcher {
            override fun catch(e: Exception) {
                exception = e
            }
        }
    }
    CompositionLocalProvider(
        LocalTheme provides DayTheme,
        LocalSpacing provides Spacing(),
        LocalCatcher provides catcher
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
interface Catcher {
    fun catch(e: Exception)
}

val LocalCatcher = staticCompositionLocalOf<Catcher> { providableCompostionLocalNotFound() }