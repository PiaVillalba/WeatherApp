package com.piavillalba.ui_weather.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piavillalba.ui_weather.extensions.glass

/**
 * A composable widget that displays the current temperature along with a location label.
 *
 * @param modifier The [Modifier] to be applied to the layout (optional).
 * @param location The name of the location (e.g. `"San Jose, CA"`).
 * @param temperatureDisplay The formatted string representing the temperature,
 * including units (e.g. `"75° F / 24° C"`).
 */
@Composable
fun TemperatureWidget(
    modifier: Modifier = Modifier,
    location: String,
    temperatureDisplay: String,
) {
    val shape = RoundedCornerShape(16.dp)
    val contentPadding = PaddingValues(horizontal = 32.dp, vertical = 24.dp)

    Column(
        modifier = modifier
            .glass(shape)
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = location,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = temperatureDisplay,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F1020) // Night color
@Composable
private fun TemperatureWidgetPreview() {
    MaterialTheme {
        TemperatureWidget(
            location = "San Jose, CA Area",
            temperatureDisplay = "75° F / 24° C"
        )
    }
}
