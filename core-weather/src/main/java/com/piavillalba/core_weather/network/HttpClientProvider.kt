package com.piavillalba.core_weather.network

import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

private const val DEFAULT_UA = "PiaWeather/1.0 (contact: pia@test.com)"
private const val CACHE_SIZE_BYTES = 5L * 1024 * 1024 // 5MB
private const val DEFAULT_TIMEOUT_SECONDS = 10L

class HttpClientProvider(
    private val userAgent: String = DEFAULT_UA,
    private val cacheDir: File? = null,
    private val logLevel: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BASIC,
) {
    private val userAgentInterceptor = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder()
                .header("User-Agent", userAgent)
                .header("Accept", "application/ld+json")
                .build()
        )
    }
    private val loggingInterceptor = HttpLoggingInterceptor().apply { this.level = logLevel }

    fun create(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(userAgentInterceptor)
            .addInterceptor(loggingInterceptor)

        cacheDir?.let { builder.cache(Cache(it, CACHE_SIZE_BYTES)) }
        return builder.build()
    }
}
