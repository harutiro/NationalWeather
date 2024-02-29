package net.harutiro.nationalweather.features.home.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.features.Weather.entities.Weather
import net.harutiro.nationalweather.features.Weather.repositories.NationwideWeatherRepository

class HomeViewModel : ViewModel() {
    val weathers = mutableStateListOf<Weather>()
    fun getWeather() {
        viewModelScope.launch {
            val nationwideWeatherRepository = NationwideWeatherRepository()
            // 参照渡しを行い、実際にはRepository側でweathersに追加している
            nationwideWeatherRepository.getNationwideWeather(weathers)
        }
    }

    fun getPrefecturalAcquisition(prefactural:String) : String {
        return prefactural.split(" ")[0]
    }
}