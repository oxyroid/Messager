package ui.scaffolds

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
    Column(modifier) {
        Box(Modifier.fillMaxWidth().heightIn(max = toolbarMaxDimen)) {
            toolbar()
        }
        Box(Modifier.weight(1f)) {
            content()
        }
    }
}