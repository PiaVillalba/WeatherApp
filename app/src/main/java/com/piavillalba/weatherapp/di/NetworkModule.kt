package com.piavillalba.weatherapp.di

import android.content.Context
import com.piavillalba.core_weather.data.remote.WeatherApi
import com.piavillalba.core_weather.network.HttpClientProvider
import com.piavillalba.core_weather.network.RetrofitProvider
import com.piavillalba.weatherapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = BuildConfig.WEATHER_BASE_URL

    @Provides
    @Singleton
    fun provideOkHttp(
        @ApplicationContext context: Context,
    ): OkHttpClient = HttpClientProvider(cacheDir = File(context.cacheDir, "okhttp_weather")).create()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit =
        RetrofitProvider(
            baseUrl = provideBaseUrl(),
            client = client
        ).create()

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}
