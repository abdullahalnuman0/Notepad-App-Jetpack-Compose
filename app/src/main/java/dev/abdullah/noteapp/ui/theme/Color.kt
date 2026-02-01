package dev.abdullah.noteapp.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val DarkGray = Color(0xFF202020)
val LightBlue = Color(0xFFD7E8DE)

val RedOrange = Color(0xffffab91)
val RedPink = Color(0xfff48fb1)
val BabyBlue = Color(0xff81deea)
val Violet = Color(0xffcf94da)
val LightGreen = Color(0xffe7ed9b)



// Light Theme Colors
object LightColors {
    // Primary Palette
    val SageGreen = Color(0xFF6B8E76)
    val SageGreenLight = Color(0xFF9BC2A6)
    val SageGreenDark = Color(0xFF4A6B56)
    val SageGreenContainer = Color(0xFFD4E5D9)

    // Neutral Palette
    val WarmWhite = Color(0xFFF8F7F2)
    val OffWhite = Color(0xFFE8E6DF)
    val LightWarmGray = Color(0xFFF2F0E9)
    val VeryLightWarm = Color(0xFFFAF8F1)

    // Text Colors
    val DarkWarmGray = Color(0xFF3D3A36)
    val MediumWarmGray = Color(0xFF4A4742)
    val LightWarmText = Color(0xFF5C5954)
    val VeryDarkWarm = Color(0xFF1E1C1A)

    // Borders & Dividers
    val WarmBorder = Color(0xFFE0DED6)
    val LightWarmBorder = Color(0xFFC9C7C0)

    // Error States
    val ErrorRed = Color(0xFFD06A5F)
    val LightErrorBg = Color(0xFFF8D7D4)
    val DarkErrorText = Color(0xFF5C1E1A)

    // Status Colors
    val Success = Color(0xFF6B8E76)
    val Warning = Color(0xFFE6A23C)
    val Info = Color(0xFF4A90E2)
}

// Dark Theme Colors
object DarkColors {
    // Primary Palette
    val SageGreen = Color(0xFF9BC2A6)
    val SageGreenDark = Color(0xFF3D5A47)
    val SageGreenContainer = Color(0xFF2D4236)

    // Background Palette
    val DarkBackground = Color(0xFF121210)
    val DarkSurface = Color(0xFF1C1C1A)
    val DarkSurfaceVariant = Color(0xFF2D2C29)
    val DarkSurfaceContainer = Color(0xFF242321)

    // Text Colors
    val LightWarmText = Color(0xFFE8E6DF)
    val MediumLightText = Color(0xFFB8B6AF)
    val LightGrayText = Color(0xFFF2F0E9)
    val VeryLightText = Color(0xFFFAF8F1)

    // Borders & Dividers
    val DarkBorder = Color(0xFF5C5954)
    val DarkerBorder = Color(0xFF3D3A36)

    // Error States
    val ErrorRed = Color(0xFFF2B8B5)
    val DarkErrorBg = Color(0xFF8C1D18)
    val LightErrorText = Color(0xFFF9DEDC)

    // Status Colors
    val Success = Color(0xFF9BC2A6)
    val Warning = Color(0xFFFFB74D)
    val Info = Color(0xFF64B5F6)
}

// Common Colors (used in both themes)
object AppColors {
    // Brand Colors
    val BrandPrimary = Color(0xFF6B8E76)
    val BrandSecondary = Color(0xFFE8E6DF)
    val BrandAccent = Color(0xFFD4A574) // Optional accent color

    // Fixed Colors (don't change with theme)
    val PureWhite = Color(0xFFFFFFFF)
    val PureBlack = Color(0xFF000000)
    val Transparent = Color(0x00000000)

    // Semantic Colors
    val Disabled = Color(0xFF9E9E9E)
    val Focus = Color(0xFF2196F3)
    val Selected = Color(0xFF1976D2)

    // Overlay Colors
    val OverlayLight = Color(0x33000000)
    val OverlayMedium = Color(0x66000000)
    val OverlayDark = Color(0x99000000)
}