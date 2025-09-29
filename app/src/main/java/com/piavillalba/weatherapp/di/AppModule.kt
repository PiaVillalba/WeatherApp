package com.piavillalba.weatherapp.di

import com.piavillalba.core_weather.data.repository.WeatherRepositoryImpl
import com.piavillalba.core_weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}
