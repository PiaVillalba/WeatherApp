package com.piavillalba.core_weather.domain.model

sealed interface WeatherError {
    data object Network : WeatherError
    data class Http(val code: Int, val msg: String?) : WeatherError
    data object EmptyForecast : WeatherError
    data class Unexpected(val cause: Throwable) : WeatherError
}

sealed interface WeatherResult<out T> {
    data class Success<T>(val value: T) : WeatherResult<T>
    data class Failure(val error: WeatherError) : WeatherResult<Nothing>
}