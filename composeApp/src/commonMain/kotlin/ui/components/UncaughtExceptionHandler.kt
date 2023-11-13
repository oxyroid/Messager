package ui.components

import Fonts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextOverflow
import ui.LocalSpacing
import ui.LocalTheme

@Composable
fun UncaughtExceptionHandler(
    exception: Exception?,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = UncaughtExceptionDialogDefaults.FontFamily
) {
    var innerException by remember { mutableStateOf(exception) }
    LaunchedEffect(exception) {
        if (exception != null) innerException = exception
    }
    AnimatedVisibility(
        visible = exception != null,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        val theme = LocalTheme.current
        val spacing = LocalSpacing.current
        val state = rememberScrollState()
        val combined = Modifier
            .fillMaxSize()
            .verticalScroll(state)
            .padding(
                start = spacing.medium,
                end = spacing.medium,
                top = spacing.medium
            )
            .then(modifier)
        Surface(
            color = theme.onSurface,
            contentColor = theme.surface
        ) {
            Column(
                modifier = combined,
                verticalArrangement = Arrangement.spacedBy(spacing.medium)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val title = remember(innerException) {
                        innerException?.let { it::class.java.simpleName }.orEmpty()
                    }
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h4,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = onDismiss
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "close"
                        )
                    }
                }

                val content = remember(innerException) {
                    innerException?.stackTraceToString().orEmpty()
                }

                SelectionContainer {
                    Text(
                        text = content,
                        fontFamily = fontFamily
                    )
                }
            }
        }
    }

}

object UncaughtExceptionDialogDefaults {
    val FontFamily = Fonts.JetbrainsMono.toFontFamily()
}