package com.example.smile.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object MoodColors {
    val Mood1Color = Color(0xFFFF4C4C)
    val Mood2Color = Color(0xFFFF7A00)
    val Mood3Color = Color(0xFFFFD600)
    val Mood4Color = Color(0xFF7DCE2E)
    val Mood5Color = Color(0xFF3CD55F)
    val DefaultMoodColor = Color(0xFFB8B7B4)
}

object MoodBorderColors {
    val Mood1BorderColor = Color(0xFFCA2D2D)
    val Mood2BorderColor = Color(0xFFCC4D00)
    val Mood3BorderColor = Color(0xFFB2A700)
    val Mood4BorderColor = Color(0xFF6BAF1D)
    val Mood5BorderColor = Color(0xFF289B3B)
    val DefaultBorderColor = Color(0xFF7A7A7A)
}

val LightColorPalette = lightColorScheme(
    primary = Color(0xFF7BC9C2),
    onPrimary = Color.Black,
    secondary = Color(0xFF7AB5B1),
    onSecondary = Color.Black,
    background = Color(0xFFFFFFFF),
    onBackground = Color.Black,
    surface = Color(0xFFF5F5F5),
    onSurface = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White
)

val DarkColorPalette = darkColorScheme(
    primary = Color(0xFF9DD8D1),
    onPrimary = Color.Black,
    secondary = Color(0xFF99C4BF),
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    error = Color(0xFFB00020),
    onError = Color.White
)



