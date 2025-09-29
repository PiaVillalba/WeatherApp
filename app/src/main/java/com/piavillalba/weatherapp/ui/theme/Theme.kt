package com.piavillalba.weatherapp.ui.theme

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

private val ColorScheme = darkColorScheme(
    primary = Indigo,
    secondary = ElectricBlue,
    tertiary = Mint,
    background = Night,
    surface = Color(0xFF17182C),
    surfaceVariant = Color(0xFF20223B),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color(0xFFEDEFFE),
    onSurface = Color(0xFFEDEFFE),
)

@Composable
fun WeatherAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}