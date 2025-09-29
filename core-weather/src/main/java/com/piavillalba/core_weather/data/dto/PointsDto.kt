package com.piavillalba.core_weather.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PointsDto(val properties: Properties) {

    @JsonClass(generateAdapter = true)
    data class Properties(val forecast: String)
}