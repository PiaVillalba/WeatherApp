package com.piavillalba.weatherapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.piavillalba.ui_weather.widgets.ErrorCard
import com.piavillalba.ui_weather.widgets.ErrorContent
import com.piavillalba.ui_weather.widgets.LoadingWidget
import com.piavillalba.ui_weather.widgets.TemperatureWidget
import com.piavillalba.weatherapp.R
import com.piavillalba.weatherapp.ui.viewmodel.MainUiState
import com.piavillalba.weatherapp.ui.viewmodel.MainViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue

@Composable
fun WeatherScreen( viewModel: MainViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        when (val state = uiState) {
            is MainUiState.Loading -> {
                LoadingWidget(loadingText = stringResource(R.string.fetching_weather))
            }
            is MainUiState.Success -> {
                val temperatureF = state.weather.temperatureF
                val temperatureC = state.weather.temperatureC
                TemperatureWidget(
                    location = stringResource(id = R.string.location_name),
                    temperatureDisplay = stringResource(
                        id = R.string.temperature_display_format,
                        temperatureF,
                        temperatureC
                    )
                )
            }
            is MainUiState.Error -> {
                val errorMessage = state.message
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
