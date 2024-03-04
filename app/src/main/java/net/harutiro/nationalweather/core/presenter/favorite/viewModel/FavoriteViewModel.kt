package net.harutiro.nationalweather.core.presenter.favorite.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity
import net.harutiro.nationalweather.features.favoriteDB.repositories.WeatherFavoriteRepository

class FavoriteViewModel(
    val weatherFavoriteRepository: WeatherFavoriteRepository = WeatherFavoriteRepository()
) : ViewModel(){

    val cityList = mutableStateListOf<WeatherFavoriteEntity>()

    fun getAll() {
        Thread {
            cityList.addAll(weatherFavoriteRepository.getFavoriteList())
            Log.d("FavoriteViewModel", cityList.toString())
        }.start()
    }

    fun insert(cityId: CityId) {
        Thread {
            val result = weatherFavoriteRepository.insertFavorite(cityId)
            if(result.isFailure){
                Log.d("FavoriteViewModel", result.exceptionOrNull().toString())
            }
        }.start()
    }

    fun delete(cityId: CityId) {
        Thread {
            val result = weatherFavoriteRepository.deleteFavorite(cityId)
            if(result.isFailure){
                Log.d("FavoriteViewModel", result.exceptionOrNull().toString())
            }
            Log.d("FavoriteViewModel", "delete")
        }.start()
    }
}