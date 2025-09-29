package com.piavillalba.core_weather.domain.usecase

import com.piavillalba.core_weather.domain.repository.WeatherRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetCurrentWeatherUseCaseTest {

    private val repository: WeatherRepository = mockk(relaxed = true)
    private val useCase = GetCurrentWeatherUseCase(repository)

    @Test
    fun `invoke should call getCurrentWeather on repository`() = runTest {
        // Given
        val lat = 37.0
        val lon = -121.0

        // When
        useCase(lat, lon)

        // Then
        coVerify(exactly = 1) { repository.getCurrentWeather(lat, lon) }
    }
}
