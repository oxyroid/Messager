package ui.toolbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun HomeToolbar(
    destination: HomeToolbarDestination,
    modifier: Modifier,
    destinationFactory: () -> List<HomeToolbarDestination>,
    onDestination: (HomeToolbarDestination) -> Unit,
) {
}