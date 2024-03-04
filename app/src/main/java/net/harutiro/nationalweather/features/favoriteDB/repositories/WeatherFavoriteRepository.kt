package net.harutiro.nationalweather.features.favoriteDB.repositories

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.apis.WeatherFavoriteApi
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity
import java.lang.Exception
import java.util.Date

class WeatherFavoriteRepository(
    private val weatherFavoriteApi: WeatherFavoriteApi = WeatherFavoriteApi()
) {
    @OptIn(DelicateCoroutinesApi::class)
    fun insertFavorite(cityId: CityId) : Deferred<Result<Unit>>{
        weatherFavoriteApi.getById(cityId)?.let {
            return GlobalScope.async {
                Result.failure(IllegalArgumentException("指定されたIDのお気に入りが既に存在します"))
            }
        }?:run{
            return GlobalScope.async {
                weatherFavoriteApi.insert(WeatherFavoriteEntity(
                    id = 0,
                    cityId = cityId,
                    createAt = Date(),
                    updateAt = Date()
                ))
                Result.success(Unit)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteFavorite(cityId:CityId): Deferred<Result<Unit>> {
        weatherFavoriteApi.getById(cityId)?.let {
            return GlobalScope.async {
                weatherFavoriteApi.delete(it)
                Result.success(Unit)
            }
        }?:run {
            return GlobalScope.async {
                Result.failure(IllegalArgumentException("指定されたIDのお気に入りが存在しません"))
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getFavoriteList(): Deferred<List<WeatherFavoriteEntity>> {
        return GlobalScope.async {
            weatherFavoriteApi.getAll().sortedBy { it.cityId.id }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun isFavorite(cityId: CityId): Deferred<Boolean> {
        return GlobalScope.async {
            weatherFavoriteApi.getById(cityId) != null
        }
    }


}