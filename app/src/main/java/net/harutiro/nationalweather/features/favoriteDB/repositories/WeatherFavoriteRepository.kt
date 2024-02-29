package net.harutiro.nationalweather.features.favoriteDB.repositories

import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.apis.WeatherFavoriteApi
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity
import java.lang.Exception
import java.util.Date

class WeatherFavoriteRepository(
    private val weatherFavoriteApi: WeatherFavoriteApi = WeatherFavoriteApi()
) {
    fun insertFavorite(cityId: CityId) : Result<Unit>{
        weatherFavoriteApi.getById(cityId)?.let {
            return Result.failure(IllegalArgumentException("指定されたIDのお気に入りが既に存在します"))
        }?:run{
            weatherFavoriteApi.insert(WeatherFavoriteEntity(
                id = 0,
                cityId = cityId,
                createAt = Date(),
                updateAt = Date()
            ))
            return Result.success(Unit)
        }
    }

    fun deleteFavorite(cityId:CityId): Result<Unit>{
        weatherFavoriteApi.getById(cityId)?.let {
            weatherFavoriteApi.delete(it)
            return Result.success(Unit)
        }?:run{
            return Result.failure(IllegalArgumentException("指定されたIDのお気に入りが存在しません"))
        }
    }

    fun getFavoriteList(): List<WeatherFavoriteEntity> {
        return weatherFavoriteApi.getAll()
    }

    fun isFavorite(cityId: CityId): Boolean {
        return weatherFavoriteApi.getById(cityId) != null
    }


}