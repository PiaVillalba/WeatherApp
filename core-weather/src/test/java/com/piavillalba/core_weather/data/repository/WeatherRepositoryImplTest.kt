package com.piavillalba.core_weather.data.repository

import com.piavillalba.core_weather.data.dto.ForecastDto
import com.piavillalba.core_weather.data.dto.PointsDto
import com.piavillalba.core_weather.data.remote.WeatherApi
import com.piavillalba.core_weather.domain.model.WeatherError
import com.piavillalba.core_weather.domain.model.WeatherResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class WeatherRepositoryImplTest {

    private val api: WeatherApi = mockk()
    private val repository = WeatherRepositoryImpl(api)

    @Test
    fun `getCurrentWeather should return Success when api calls are successful`() = runTest {
        // Given
        val lat = 37.0
        val lon = -121.0
        val pointsDto = PointsDto("forecast_url")
        val forecastDto = ForecastDto(
            periods = listOf(
                ForecastDto.Period(temperature = 75, temperatureUnit = "F")
            )
        )

        coEvery { api.getPoints(lat, lon) } returns pointsDto
        coEvery { api.getForecast(pointsDto.forecast) } returns forecastDto

        // When
        val result = repository.getCurrentWeather(lat, lon)

        // Then
        assertTrue(result is WeatherResult.Success)
    }

    @Test
    fun `getCurrentWeather should return Network Failure when getPoints throws IOException`() = runTest {
        // Given
        val lat = 37.0
        val lon = -121.0
        coEvery { api.getPoints(lat, lon) } throws IOException()

        // When
        val result = repository.getCurrentWeather(lat, lon)

        // Then
        assertTrue(result is WeatherResult.Failure)
        assertEquals(WeatherError.Network, (result as WeatherResult.Failure).error)
    }

    @Test
    fun `getCurrentWeather should return Api Failure when getForecast throws HttpException`() = runTest {
        // Given
        val lat = 37.0
        val lon = -121.0
        val pointsDto = PointsDto("forecast_url")
        val errorBody = """{"error":"server"}"""
            .toResponseBody("application/json".toMediaType())
        val httpException = HttpException(Response.error<Any>(500, errorBody))

        coEvery { api.getPoints(lat, lon) } returns pointsDto
        coEvery { api.getForecast(pointsDto.forecast) } throws httpException

        // When
        val result = repository.getCurrentWeather(lat, lon)

        // Then
        assertTrue(result is WeatherResult.Failure)
        val err = (result as WeatherResult.Failure).error
        assertTrue(err is WeatherError.Http)

        val http = err as WeatherError.Http
        assertEquals(500, http.code)

  }

    @Test
    fun `getCurrentWeather should return EmptyForecast Failure when periods are empty`() = runTest {
        // Given
        val lat = 37.0
        val lon = -121.0
        val pointsDto = PointsDto("forecast_url")
        val forecastDto = ForecastDto(periods = emptyList())

        coEvery { api.getPoints(lat, lon) } returns pointsDto
        coEvery { api.getForecast(pointsDto.forecast) } returns forecastDto

        // When
        val result = repository.getCurrentWeather(lat, lon)

        // Then
        assertTrue(result is WeatherResult.Failure)
        assertEquals(WeatherError.EmptyForecast, (result as WeatherResult.Failure).error)
    }
}
