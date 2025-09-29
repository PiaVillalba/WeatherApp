package com.piavillalba.weatherapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piavillalba.core_weather.domain.model.WeatherError
import com.piavillalba.core_weather.domain.model.WeatherResult
import com.piavillalba.core_weather.domain.usecase.GetCurrentWeatherUseCase
import com.piavillalba.weatherapp.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        fetchCurrentWeather()
    }

    fun retryCurrentWeather() {
        fetchCurrentWeather()
    }

    private fun fetchCurrentWeather() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            val lat = BuildConfig.DEFAULT_LATITUDE
            val lon = BuildConfig.DEFAULT_LONGITUDE
            when (val result = getCurrentWeatherUseCase(lat, lon)) {
                is WeatherResult.Success -> {
                    _uiState.value = MainUiState.Success(result.value)
                }
                is WeatherResult.Failure -> {
                    _uiState.value = MainUiState.Error(result.error)
                }
            }
        }
    }
}