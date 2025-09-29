package com.piavillalba.weatherapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piavillalba.core_weather.domain.model.WeatherError
import com.piavillalba.ui_weather.widgets.ErrorCard
import com.piavillalba.ui_weather.widgets.ErrorContent
import com.piavillalba.ui_weather.widgets.LoadingWidget
import com.piavillalba.ui_weather.widgets.TemperatureWidget
import com.piavillalba.weatherapp.R
import com.piavillalba.weatherapp.ui.viewmodel.MainUiState
import com.piavillalba.weatherapp.ui.viewmodel.MainViewModel

@Composable
fun WeatherScreen(
    windowSizeClass: WindowWidthSizeClass,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val state = uiState) {
            is MainUiState.Loading -> {
                LoadingWidget(loadingText = stringResource(R.string.fetching_weather))
            }
            is MainUiState.Success -> {
                val weather = state.weather
                TemperatureWidget(
                    location = stringResource(id = R.string.location_name),
                    temperatureDisplay = stringResource(
                        id = R.string.temperature_display_format,
                        weather.temperatureF,
                        weather.temperatureC
                    ),
                    windowSizeClass = windowSizeClass
                )
            }
            is MainUiState.Error -> {
                val errorMessage = when (val error = state.error) {
                    is WeatherError.Http -> stringResource(id = R.string.error_http, error.code)
                    WeatherError.Network -> stringResource(id = R.string.error_network)
                    WeatherError.EmptyForecast -> stringResource(id = R.string.error_empty_forecast)
                    is WeatherError.Unexpected -> stringResource(id = R.string.error_unexpected)
                }
                ErrorCard {
                    ErrorContent(
                        title = "Upss",
                        message = errorMessage,
                        retryText = "Retry",
                        onRetry = viewModel::retryCurrentWeather
                    )


                }

            }
        }
    }
}
