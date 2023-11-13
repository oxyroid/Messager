package ui.toolbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun HomeToolbar(
    modifier: Modifier,
    destinationFactory: () -> List<HomeToolbarDestination>,
    destination: HomeToolbarDestination,
    onDestination: (HomeToolbarDestination) -> Unit,
) {
}