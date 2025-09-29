package com.piavillalba.core_weather.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherTest {

    @Test
    fun `temperatureC should convert fahrenheit to celsius correctly`() {
        // Given
        val weather = Weather(temperatureF = 75, unit = "F")

        // When
        val celsius = weather.temperatureC

        // Then
        assertEquals(23, celsius)
    }

    @Test
    fun `temperatureC should handle freezing point correctly`() {
        // Given
        val weather = Weather(temperatureF = 32, unit = "F")

        // When
        val celsius = weather.temperatureC

        // Then
        assertEquals(0, celsius)
    }
}
