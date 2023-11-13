package ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class Theme(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color
)

val LocalTheme = compositionLocalOf<Theme> { providableCompostionLocalNotFound() }

val DayTheme = Theme(
    primary = Color(0xff837fc9),
    onPrimary = Color.White,
    background = Color(0xff181818),
    onBackground = Color.White,
    surface = Color(0xFFeeeeee),
    onSurface = Color(0xff232325),
)