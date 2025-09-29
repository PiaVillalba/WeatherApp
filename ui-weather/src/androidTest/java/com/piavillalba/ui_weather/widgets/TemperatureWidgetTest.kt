package com.piavillalba.ui_weather.widgets

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class TemperatureWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun temperatureWidget_displaysCorrectData() {
        // Given
        val location = "San Jose, CA"
        val temperature = "75° F / 24° C"

        // When
        composeTestRule.setContent {
            TemperatureWidget(
                location = location,
                temperatureDisplay = temperature
            )
        }

        // Then
        composeTestRule.onNodeWithText(location).assertExists()
        composeTestRule.onNodeWithText(temperature).assertExists()
    }
}
