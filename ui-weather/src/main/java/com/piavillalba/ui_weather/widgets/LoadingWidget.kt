package com.piavillalba.ui_weather.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * A composable widget that displays a loading indicator with a label.
 *
 * @param modifier The [Modifier] to be applied to the layout (optional).
 * @param loadingText The text to display below the circular loading indicator.
 */
@Composable
fun LoadingWidget(
    modifier: Modifier = Modifier,
    loadingText: String
) {
    Column(
        modifier = modifier,
         horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = loadingText,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

/**
 * A preview of the [LoadingWidget] with a night background and default text.
 *
 * @see LoadingWidget
 */
@Preview(showBackground = true)
@Composable
private fun LoadingWidgetPreview() {
    LoadingWidget(loadingText = "Fetching weather...")
}
