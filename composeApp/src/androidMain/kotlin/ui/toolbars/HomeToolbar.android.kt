package ui.toolbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun HomeToolbar(
    destination: HomeDestination,
    modifier: Modifier,
    destinationFactory: () -> List<HomeDestination>,
    onDestination: (HomeDestination) -> Unit,
) {
}