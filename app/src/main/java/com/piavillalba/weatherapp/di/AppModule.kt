package com.piavillalba.weatherapp.di

import com.piavillalba.core_weather.data.repository.WeatherRepositoryImpl
import com.piavillalba.core_weather.domain.repository.WeatherRepository
import com.piavillalba.core_weather.domain.usecase.GetCurrentWeatherUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository {
        return weatherRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideGetCurrentWeatherUseCase(weatherRepository: WeatherRepository): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(weatherRepository)
    }

}
