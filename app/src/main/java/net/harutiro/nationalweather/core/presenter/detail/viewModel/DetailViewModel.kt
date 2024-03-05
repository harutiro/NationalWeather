package net.harutiro.nationalweather.core.presenter.detail.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather
import net.harutiro.nationalweather.features.Weather.repositories.NationwideWeatherRepository
import net.harutiro.nationalweather.features.Weather.repositories.NationwideWeatherRepositoryImpl
import net.harutiro.nationalweather.features.favoriteDB.repositories.WeatherFavoriteRepository
import net.harutiro.nationalweather.features.favoriteDB.repositories.WeatherFavoriteRepositoryImpl

class DetailViewModel(
    val nationwideWeatherRepository: NationwideWeatherRepository = NationwideWeatherRepositoryImpl(),
    val weatherFavoriteRepository: WeatherFavoriteRepository = WeatherFavoriteRepositoryImpl()
) : ViewModel() {
    val weather = mutableStateOf<Weather?>(null)
    val city = mutableStateOf<CityId?>(null)
    val bookmark = mutableStateOf(false)

    fun getWeather(cityId: CityId) {

        Log.d("DetailViewModel", "cityId: $cityId")

        viewModelScope.launch(Dispatchers.IO) {
            // 参照渡しを行い、実際にはRepository側でweathersに追加している
            weather.value = nationwideWeatherRepository.getPrefectureWeather(cityId)
            city.value = cityId
            bookmark.value = weatherFavoriteRepository.isFavorite(cityId).await()
        }
    }

    fun updateBookmark(
        showSnackBar: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO){
            bookmark.value = !bookmark.value
            if (bookmark.value) {
                weatherFavoriteRepository.insertFavorite(city.value!!).await()
                showSnackBar("お気に入りに追加しました")
            } else {
                weatherFavoriteRepository.deleteFavorite(city.value!!).await()
                showSnackBar("お気に入りから削除しました")
            }
        }
    }
}