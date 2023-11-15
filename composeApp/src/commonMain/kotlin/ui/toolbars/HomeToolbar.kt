package ui.toolbars

import androidx.compose.animation.Crossfade
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
expect fun HomeToolbar(
    destination: HomeToolbarDestination,
    modifier: Modifier = Modifier,
    destinationFactory: () -> List<HomeToolbarDestination> = { HomeToolbarDestination.entries },
    onDestination: (HomeToolbarDestination) -> Unit,
)

@Composable
fun HomeToolbarItem(
    selected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Crossfade(selected) { selected ->
            if (selected) {
                Icon(
                    imageVector = selectedIcon,
                    contentDescription = contentDescription
                )
            } else {
                Icon(
                    imageVector = unselectedIcon,
                    contentDescription = contentDescription
                )
            }
        }
    }
}

enum class HomeToolbarDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val contentDescription: String?
) {
    MAILS(
        selectedIcon = Icons.Rounded.Email,
        unselectedIcon = Icons.Outlined.Email,
        contentDescription = "mails"
    ),
    SETTINGS(
        selectedIcon = Icons.Rounded.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        contentDescription = "settings"
    )
}