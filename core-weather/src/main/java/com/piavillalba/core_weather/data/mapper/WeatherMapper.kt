package com.piavillalba.core_weather.data.mapper

import com.piavillalba.core_weather.data.dto.ForecastDto
import com.piavillalba.core_weather.domain.model.Weather

fun ForecastDto.Period.toDomainModel() : Weather =
    Weather(
        temperatureF = temperature,
        unit = temperatureUnit
    )