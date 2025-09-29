package com.piavillalba.core_weather.domain.usecase

import com.piavillalba.core_weather.domain.model.Weather
import com.piavillalba.core_weather.domain.model.WeatherResult
import com.piavillalba.core_weather.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lon: Double): WeatherResult<Weather> {
        return repository.getCurrentWeather(lat, lon)
    }
}