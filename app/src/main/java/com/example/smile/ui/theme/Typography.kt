package com.example.smile.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

val customFontFamily = FontFamily.Default

val appTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 30.sp,
        letterSpacing = 0.15.sp
    ),
    displayMedium = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 24.sp,
        letterSpacing = 0.15.sp
    ),
    displaySmall = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 14.sp,
        letterSpacing = 0.15.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp
    ),
    labelMedium = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    labelSmall = TextStyle(
        fontFamily = customFontFamily,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp
    )
)

