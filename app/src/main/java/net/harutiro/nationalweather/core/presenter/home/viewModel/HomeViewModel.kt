package net.harutiro.nationalweather.core.presenter.home.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.features.Weather.entities.Weather
import net.harutiro.nationalweather.features.Weather.repositories.NationwideWeatherRepository
import net.harutiro.nationalweather.features.Weather.repositories.NationwideWeatherRepositoryImpl

class HomeViewModel(
    val nationwideWeatherRepository: NationwideWeatherRepository = NationwideWeatherRepositoryImpl()
) : ViewModel() {
    val weathers = mutableStateListOf<Weather>()

    val isLoading = mutableStateOf(false)
    fun getWeather() {
        viewModelScope.launch {
            // 参照渡しを行い、実際にはRepository側でweathersに追加している
            isLoading.value = true
            Log.d("HomeViewModel", "始まり")
            nationwideWeatherRepository.getNationwideWeather(weathers).join()
            Log.d("HomeViewModel", "終わり")
            isLoading.value = false
        }
    }
}