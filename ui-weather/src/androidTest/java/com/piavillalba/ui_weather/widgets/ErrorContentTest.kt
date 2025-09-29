package com.piavillalba.ui_weather.widgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ErrorContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorContent_displaysCorrectData() {
        // Given
        val title = "Error Title"
        val message = "Error message."
        val retryText = "Retry"

        // When
        composeTestRule.setContent {
            ErrorContent(
                title = title,
                message = message,
                retryText = retryText,
                onRetry = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText(title).assertExists()
        composeTestRule.onNodeWithText(message).assertExists()
        composeTestRule.onNodeWithText(retryText).assertExists()
    }

    @Test
    fun errorContent_callsOnRetryWhenButtonClicked() {
        // Given
        val retryText = "Retry"
        val onRetry: () -> Unit = mock()

        // When
        composeTestRule.setContent {
            ErrorContent(
                title = "Title",
                message = "Message",
                retryText = retryText,
                onRetry = onRetry
            )
        }

        composeTestRule.onNodeWithText(retryText).performClick()

        // Then
        verify(onRetry).invoke()
    }
}
