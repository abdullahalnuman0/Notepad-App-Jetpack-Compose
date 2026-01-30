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

private val LightColorScheme = lightColorScheme(
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