package com.piavillalba.ui_weather.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piavillalba.ui_weather.extensions.glass

/**
 * A composable widget that displays the current temperature along with a location label.
 * Its internal layout adapts based on the provided [windowSizeClass].
 *
 * @param modifier The [Modifier] to be applied to the layout (optional).
 * @param location The name of the location (e.g. `"San Jose, CA"`).
 * @param temperatureDisplay The formatted string representing the temperature,
 * including units (e.g. `"75° F / 24° C"`).
 * @param windowSizeClass The current window width size class, used to determine the internal layout.
 */
@Composable
fun TemperatureWidget(
    modifier: Modifier = Modifier,
    location: String,
    temperatureDisplay: String,
    windowSizeClass: WindowWidthSizeClass
) {
    val shape = RoundedCornerShape(16.dp)
    val contentPadding = PaddingValues(horizontal = 32.dp, vertical = 24.dp)

    when (windowSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = modifier
                    .glass(shape)
                    .padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LocationText(location)
                Spacer(modifier = Modifier.height(16.dp))
                TemperatureText(temperatureDisplay)
            }
        }
        else -> { // Medium and Expanded
            Row(
                modifier = modifier
                    .glass(shape)
                    .padding(contentPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                LocationText(location)
                Spacer(modifier = Modifier.width(24.dp))
                TemperatureText(temperatureDisplay)
            }
        }
    }
}

@Composable
private fun LocationText(location: String) {
    Text(
        text = location,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun TemperatureText(temperatureDisplay: String) {
    Text(
        text = temperatureDisplay,
        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Preview(name = "Compact Layout", showBackground = true, backgroundColor = 0xFF0F1020)
@Composable
private fun TemperatureWidgetCompactPreview() {
    MaterialTheme {
        TemperatureWidget(
            location = "San Jose, CA Area",
            temperatureDisplay = "75° F / 24° C",
            windowSizeClass = WindowWidthSizeClass.Compact
        )
    }
}

@Preview(name = "Expanded Layout", showBackground = true, backgroundColor = 0xFF0F1020)
@Composable
private fun TemperatureWidgetExpandedPreview() {
    MaterialTheme {
        TemperatureWidget(
            location = "San Jose, CA Area",
            temperatureDisplay = "75° F / 24° C",
            windowSizeClass = WindowWidthSizeClass.Expanded
        )
    }
}
