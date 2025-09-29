package com.piavillalba.ui_weather.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * A custom modifier that applies a glassmorphism effect to a composable.
 *
 * This includes a semi-transparent background, a clipped shape, and a subtle border.
 *
 * @param shape The shape to clip the content to.
 */
fun Modifier.glass(shape: Shape) = composed {
    val surfaceColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
    val borderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
    this
        .clip(shape)
        .background(surfaceColor)
        .border(1.dp, borderColor, shape)
}
