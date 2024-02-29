package net.harutiro.nationalweather.core.presenter.favorite.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.repositories.WeatherFavoriteRepository

class FavoriteViewModel : ViewModel(){

    private val weatherFavoriteRepository = WeatherFavoriteRepository()

    fun getAll() {
        Thread {
            val list = weatherFavoriteRepository.getFavoriteList()
            Log.d("FavoriteViewModel", list.toString())
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