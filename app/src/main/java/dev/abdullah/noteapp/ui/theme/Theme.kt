package dev.abdullah.noteapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/*private val DarkColorScheme = darkColorScheme(
    primary = Color.White,
    background = DarkGray,
    onBackground = Color.White,
    secondary = LightBlue,
    tertiary = DarkGray,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    *//* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    *//*
)*/

/*private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6B8E76),
    onPrimary = Color.White,
    secondary = Color(0xFFE8E6DF),
    onSecondary = Color(0xFF4A4742),
    tertiary = Color(0xFFF2F0E9),
    onTertiary = Color(0xFF3D3A36),
    background = Color(0xFFF8F7F2),
    onBackground = Color(0xFF3D3A36),
    surface = Color.White,
    onSurface = Color(0xFF3D3A36),
    error = Color(0xFFD06A5F),
    onError = Color.White,
    outline = Color(0xFFE0DED6)
)

private val DarkColorScheme = darkColorScheme(
    // Define dark theme colors if needed
    primary = Color(0xFF7DA686),
    background = Color(0xFF121210)
)*/

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6B8E76),        // Sage green
    onPrimary = Color(0xFFFFFFFF),      // White on sage
    primaryContainer = Color(0xFFD4E5D9), // Light sage background
    onPrimaryContainer = Color(0xFF1F3528), // Dark text on light sage

    secondary = Color(0xFFE8E6DF),      // Warm off-white
    onSecondary = Color(0xFF4A4742),    // Warm gray text
    secondaryContainer = Color(0xFFF5F3EC), // Lighter warm white
    onSecondaryContainer = Color(0xFF2B2926), // Darker warm gray

    tertiary = Color(0xFFF2F0E9),       // Light warm gray
    onTertiary = Color(0xFF3D3A36),     // Warm dark gray
    tertiaryContainer = Color(0xFFFAF8F1), // Very light warm white
    onTertiaryContainer = Color(0xFF1E1C1A), // Very dark warm gray

    background = Color(0xFFF8F7F2),     // Main background
    onBackground = Color(0xFF3D3A36),   // Main text color

    surface = Color(0xFFFFFFFF),        // Cards, sheets
    onSurface = Color(0xFF3D3A36),      // Text on surface
    surfaceVariant = Color(0xFFF0EEE7), // Slightly different surface
    onSurfaceVariant = Color(0xFF5C5954), // Secondary text

    outline = Color(0xFFE0DED6),        // Borders, dividers
    outlineVariant = Color(0xFFC9C7C0), // Lighter borders

    error = Color(0xFFD06A5F),          // Error red
    onError = Color(0xFFFFFFFF),        // White on error
    errorContainer = Color(0xFFF8D7D4), // Light error background
    onErrorContainer = Color(0xFF5C1E1A), // Dark text on error bg
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9BC2A6),        // Brighter sage green for dark mode
    onPrimary = Color(0xFF1A3624),      // Very dark green on sage
    primaryContainer = Color(0xFF3D5A47), // Dark sage background
    onPrimaryContainer = Color(0xFFD4E5D9), // Light text on dark sage

    secondary = Color(0xFF3A3935),      // Dark warm gray
    onSecondary = Color(0xFFE8E6DF),    // Light warm text
    secondaryContainer = Color(0xFF2C2B28), // Darker warm gray
    onSecondaryContainer = Color(0xFFF5F3EC), // Light text on dark gray

    tertiary = Color(0xFF2D2C29),       // Dark gray for tertiary
    onTertiary = Color(0xFFF2F0E9),     // Light text
    tertiaryContainer = Color(0xFF242321), // Even darker gray
    onTertiaryContainer = Color(0xFFFAF8F1), // Very light text

    background = Color(0xFF121210),     // Dark background
    onBackground = Color(0xFFE8E6DF),   // Light text on dark background

    surface = Color(0xFF1C1C1A),        // Dark cards, sheets
    onSurface = Color(0xFFE8E6DF),      // Light text on surface
    surfaceVariant = Color(0xFF2D2C29), // Slightly different dark surface
    onSurfaceVariant = Color(0xFFB8B6AF), // Secondary light text

    outline = Color(0xFF5C5954),        // Dark borders
    outlineVariant = Color(0xFF3D3A36), // Even darker borders

    error = Color(0xFFF2B8B5),          // Lighter error red for dark mode
    onError = Color(0xFF601410),        // Dark red text
    errorContainer = Color(0xFF8C1D18), // Dark error background
    onErrorContainer = Color(0xFFF9DEDC), // Light text on error bg

    scrim = Color(0x66000000),          // Overlay/scrim color
    inverseSurface = Color(0xFFE8E6DF), // Inverse surface color
    inverseOnSurface = Color(0xFF3D3A36), // Inverse text color
    inversePrimary = Color(0xFF6B8E76), // Inverse primary
)



@Composable
fun NoteAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}