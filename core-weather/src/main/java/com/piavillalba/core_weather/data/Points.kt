package com.piavillalba.core_weather.data


data class PointsDto(
    val properties: PointsProps
)
data class PointsProps(
    val forecast: String // "https://api.weather.gov/gridpoints/MTR/100,80/forecast"
)

// ForecastDto.kt
data class ForecastDto(
    val properties: ForecastProps
)
data class ForecastProps(
    val periods: List<PeriodDto>
)
data class PeriodDto(
    val temperature: Int,
    val temperatureUnit: String // "F"
)
