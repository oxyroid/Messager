package ui.scaffolds

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun MailScaffold(
    list: @Composable () -> Unit,
    detail: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier
)