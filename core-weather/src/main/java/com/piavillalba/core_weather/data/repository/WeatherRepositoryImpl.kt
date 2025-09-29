package com.piavillalba.core_weather.data.repository

import com.piavillalba.core_weather.data.remote.WeatherApi
import com.piavillalba.core_weather.domain.model.Weather
import com.piavillalba.core_weather.domain.model.WeatherError
import com.piavillalba.core_weather.domain.model.WeatherResult
import com.piavillalba.core_weather.domain.repository.WeatherRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {
    override suspend fun getCurrentWeather(lat: Double, lon: Double): WeatherResult<Weather> {
        return try {
            val points = api.getPoints(lat, lon)
            val forecast = api.getForecast(points.forecast)
            val currentForecast = forecast.periods.firstOrNull() ?: return WeatherResult.Failure(WeatherError.EmptyForecast)
            WeatherResult.Success(currentForecast.toDomainModel())
        } catch (io: IOException) {
            WeatherResult.Failure(WeatherError.Network)
        } catch (he: HttpException) {
            WeatherResult.Failure(WeatherError.Http(he.code(), he.message()))
        } catch (e: Exception) {
            WeatherResult.Failure(WeatherError.Unexpected(e))
        }
    }
}