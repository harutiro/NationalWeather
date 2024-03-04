package net.harutiro.nationalweather.core.presenter.favorite.viewModel

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather
import net.harutiro.nationalweather.features.Weather.repositories.NationwideWeatherRepository
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity
import net.harutiro.nationalweather.features.favoriteDB.repositories.WeatherFavoriteRepository

class FavoriteViewModel(
    val weatherFavoriteRepository: WeatherFavoriteRepository = WeatherFavoriteRepository(),
    val weatherRepository: NationwideWeatherRepository = NationwideWeatherRepository()
) : ViewModel(){

    val cityList = mutableStateListOf<WeatherFavoriteEntity>()
    val weatherList = mutableStateListOf<Weather>()

    val isLoading = mutableStateOf(false)

    suspend fun getWeather(cityId: CityId): Weather {
        Log.d("FavoriteViewModel", "cityId: $cityId")
        return weatherRepository.getPrefectureWeather(cityId)
    }

    suspend fun getFavoriteAll() {
        Log.d("FavoriteViewModel", "読み取り始める")
        isLoading.value = true
        cityList.clear()
        weatherList.clear()
        cityList.addAll(weatherFavoriteRepository.getFavoriteList().await())
        Log.d("FavoriteViewModel", cityList.count().toString())
        cityList.forEach {
            weatherList.add(getWeather(it.cityId))
            Log.d("FavoriteViewModel", it.cityId.name)
        }
        isLoading.value = false
        Log.d("FavoriteViewModel", "読み取り終わり")
    }

    suspend fun insertFavorite(cityId: CityId) {
        val result = weatherFavoriteRepository.insertFavorite(cityId).await()
        if(result.isFailure){
            Log.d("FavoriteViewModel", result.exceptionOrNull().toString())
        }
    }

    suspend fun deleteFavorite(cityId: CityId) {
        val result = weatherFavoriteRepository.deleteFavorite(cityId).await()
        if(result.isFailure){
            Log.d("FavoriteViewModel", result.exceptionOrNull().toString())
        }
        Log.d("FavoriteViewModel", "delete")
    }

    fun checkFavorite(cityId: CityId): Boolean {
        return cityList.any { it.cityId == cityId }
    }

    suspend fun updateBookmark(
        cityId: CityId,
        showSnackBar: (String) -> Unit
    ) {
        if(checkFavorite(cityId)){
            deleteFavorite(cityId)
            showSnackBar("お気に入りから削除しました")
        }else{
            insertFavorite(cityId)
            showSnackBar("お気に入りに追加しました")
        }
        cityList.remove(cityList.find { it.cityId == cityId })
        weatherList.remove(weatherList.find { it.cityId == cityId })
    }
}