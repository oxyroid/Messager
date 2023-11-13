package ui.scaffolds

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
expect fun HomeScaffold(
    toolbarMaxDimen: Dp = HomeScaffoldDefaults.ToolBarMaxDimen,
    toolbar: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
)

object HomeScaffoldDefaults {
    val ToolBarMaxDimen = 64.dp
}