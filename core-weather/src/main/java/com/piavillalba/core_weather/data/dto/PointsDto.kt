package com.piavillalba.core_weather.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PointsDto(val forecast: String)