package com.piavillalba.core_weather.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDto(
    val periods: List<Period>
) {
    @JsonClass(generateAdapter = true)
    data class Period(
        val temperature: Int,
        val temperatureUnit: String
    )
}