package net.harutiro.nationalweather.features.detail.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather
import net.harutiro.nationalweather.features.Weather.repositories.NationwideWeatherRepository

class DetailViewModel : ViewModel() {
    val weather = mutableStateOf<Weather?>(null)
    val city = mutableStateOf<CityId?>(null)
    fun getWeather(cityId: CityId) {
        viewModelScope.launch {
            val nationwideWeatherRepository = NationwideWeatherRepository()
            // 参照渡しを行い、実際にはRepository側でweathersに追加している
            weather.value = nationwideWeatherRepository.getPrefectureWeather(cityId)
            city.value = cityId
        }
    }
}