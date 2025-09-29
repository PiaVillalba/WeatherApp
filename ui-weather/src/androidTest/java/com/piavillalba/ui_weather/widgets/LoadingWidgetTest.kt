package com.piavillalba.ui_weather.widgets

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class LoadingWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingWidget_displaysCorrectText() {
        // Given
        val loadingText = "Fetching weather..."

        // When
        composeTestRule.setContent {
            LoadingWidget(loadingText = loadingText)
        }

        // Then
        composeTestRule.onNode(
            hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate)
        ).assertExists()

        // Text
        composeTestRule.onNodeWithText(loadingText).assertExists()
    }
}
