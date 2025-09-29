package com.piavillalba.core_weather.domain.model

data class Weather(
    val temperatureF: Int,
    val unit: String
) {
    val temperatureC: Int get() = fahrenheitToCelsius(temperatureF)
}
fun fahrenheitToCelsius(fahrenheit: Int): Int {
    return ((fahrenheit - 32) * 5.0 / 9.0).toInt()
}