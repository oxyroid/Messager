package ui.scaffolds

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun MailScaffold(
    list: @Composable () -> Unit,
    detail: (@Composable () -> Unit)?,
    modifier: Modifier
) {
}