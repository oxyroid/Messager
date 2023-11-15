package feature.mail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.mail.list.ListRoute
import ui.scaffolds.MailScaffold

@Composable
fun MailRoute(
    modifier: Modifier = Modifier
) {
    MailScaffold(
        list = {
            ListRoute()
        },
        detail = @Composable {

        },
        modifier = modifier
    )
}