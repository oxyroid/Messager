package feature.mail

import LocalSentry
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import feature.mail.list.ListRoute
import kotlinx.coroutines.delay
import ui.scaffolds.MailScaffold
import kotlin.time.Duration.Companion.seconds

@Composable
fun MailRoute(
    modifier: Modifier = Modifier
) {
    val catcher = LocalSentry.current
    MailScaffold(
        list = {
            ListRoute()
        },
        detail = @Composable {
            LaunchedEffect(Unit) {
                delay(3.seconds)
                catcher.catch(RuntimeException("Hello!"))
            }
        },
        modifier = modifier
    )
}