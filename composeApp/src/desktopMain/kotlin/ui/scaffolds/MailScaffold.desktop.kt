package ui.scaffolds

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import ui.LocalSpacing
import ui.LocalTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun MailScaffold(
    list: @Composable () -> Unit,
    detail: (@Composable () -> Unit)?,
    modifier: Modifier
) {
    val theme = LocalTheme.current
    Row {
        val windowInfo = LocalWindowInfo.current
        val modularity = with(windowInfo.containerSize) { width > height }

        Box(Modifier.fillMaxHeight().weight(3f)) { list() }
        if (modularity) {
            Box(Modifier.fillMaxHeight().weight(5f)) {
                val showDetail = detail != null
                val currentBackgroundColor by animateColorAsState(
                    targetValue = if (showDetail) theme.primary
                    else Color.Gray
                )
                val currentContentColor by animateColorAsState(
                    targetValue = if (showDetail) theme.onPrimary
                    else Color.Black
                )
                MailScaffoldDetailBackground(
                    backgroundColor = currentBackgroundColor,
                    contentColor = currentContentColor
                )
                if (showDetail) {
                    detail?.invoke()
                }
            }
        }

    }
}

@Composable
private fun MailScaffoldDetailBackground(
    backgroundColor: Color,
    contentColor: Color
) {
    val spacing = LocalSpacing.current
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = spacing.none,
        modifier = Modifier.fillMaxSize()
    ) { }
}