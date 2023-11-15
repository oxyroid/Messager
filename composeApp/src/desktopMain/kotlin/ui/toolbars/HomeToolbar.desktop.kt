package ui.toolbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.LocalSpacing
import ui.LocalTheme

@Composable
actual fun HomeToolbar(
    destination: HomeDestination,
    modifier: Modifier,
    destinationFactory: () -> List<HomeDestination>,
    onDestination: (HomeDestination) -> Unit,
) {
    val spacing = LocalSpacing.current
    val theme = LocalTheme.current
    Surface(
        color = theme.onSurface,
        contentColor = theme.surface
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .selectableGroup()
                .padding(spacing.medium)
        ) {
            val destinations = destinationFactory()
            if (destinations.isNotEmpty()) {
                destinations.forEach { current ->
                    HomeToolbarItem(
                        selected = current == destination,
                        selectedIcon = current.selectedIcon,
                        unselectedIcon = current.unselectedIcon,
                        contentDescription = current.contentDescription
                    ) {
                        onDestination(current)
                    }
                }
            }
        }
    }
}