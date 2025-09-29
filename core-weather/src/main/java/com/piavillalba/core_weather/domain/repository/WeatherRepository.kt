package com.piavillalba.core_weather.domain.repository

import com.piavillalba.core_weather.domain.model.Weather
import com.piavillalba.core_weather.domain.model.WeatherResult

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double): WeatherResult<Weather>

}