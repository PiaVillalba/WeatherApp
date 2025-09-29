package com.piavillalba.core_weather.data.remote

import com.piavillalba.core_weather.data.dto.ForecastDto
import com.piavillalba.core_weather.data.dto.PointsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface WeatherApi {
    @GET("points/{lat},{lon}")
    suspend fun getPoints(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double
    ): PointsDto

    @GET
    suspend fun getForecast(@Url forecastUrl: String): ForecastDto
}