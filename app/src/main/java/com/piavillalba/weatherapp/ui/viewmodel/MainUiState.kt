package com.piavillalba.weatherapp.ui.viewmodel

import com.piavillalba.core_weather.domain.model.Weather

sealed interface MainUiState {
    data object Loading : MainUiState
    data class Success(val weather: Weather) : MainUiState
    data class Error(val message: String) : MainUiState
}