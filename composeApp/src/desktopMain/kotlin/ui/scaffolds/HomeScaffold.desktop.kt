package ui.scaffolds

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
actual fun HomeScaffold(
    toolbarMaxDimen: Dp,
    toolbar: @Composable () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    Row(modifier) {
        Box(Modifier.fillMaxHeight().widthIn(max = toolbarMaxDimen)) {
            toolbar()
        }
        Box(Modifier.weight(1f)) {
            content()
        }
    }
}