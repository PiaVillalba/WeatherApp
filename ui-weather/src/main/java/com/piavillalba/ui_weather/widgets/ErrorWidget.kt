package com.piavillalba.ui_weather.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudOff

/**
 * This composable provides a `Card` with error-specific theming. The content is provided via a slot.
 *
 * @param modifier The modifier to be applied to the card.
 * @param content The content to be displayed inside the card.
 */
@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
        elevation = CardDefaults.cardElevation(6.dp),
        content = content
    )
}

/**
 * The content layout for an error state, including an icon, title, message, and retry button.
 *
 * This composable is designed to be placed inside an `ErrorCard` or any other container.
 *
 * @param title The title of the error.
 * @param message The detailed error message.
 * @param retryText The text for the retry button.
 * @param onRetry The lambda to be invoked when the retry button is clicked.
 * @param modifier The modifier to be applied to the layout.
 * @param leadingIcon An optional slot for a leading icon. Defaults to a cloud icon.
 */
@Composable
fun ErrorContent(
    title: String,
    message: String,
    retryText: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable ((iconColor: Color) -> Unit)? = { iconColor ->
        Icon(
            imageVector = Icons.Rounded.CloudOff,
            contentDescription = null,
            tint = iconColor
        )
    }
) {
    val onContainerColor = MaterialTheme.colorScheme.onErrorContainer

    Column(
        modifier = modifier.padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            leadingIcon?.invoke(onContainerColor)
            Spacer(Modifier.width(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = onContainerColor
            )
        }
        Spacer(Modifier.height(10.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = onContainerColor
        )
        Spacer(Modifier.height(16.dp))
        OutlinedButton(
            onClick = onRetry,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = onContainerColor),
            border = BorderStroke(1.dp, onContainerColor),
        ) {
            Text(retryText)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorCardPreview() {
    ErrorCard {
        ErrorContent(
            title = "We couldn’t fetch weather",
            message = "Check your internet connection and try again.",
            retryText = "Retry",
            onRetry = {},
        )
    }
}
