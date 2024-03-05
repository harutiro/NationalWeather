package net.harutiro.nationalweather.features.favoriteDB.repositories

import kotlinx.coroutines.Deferred
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity

interface WeatherFavoriteRepository {
    fun insertFavorite(cityId: CityId) : Deferred<Result<Unit>>
    fun deleteFavorite(cityId:CityId): Deferred<Result<Unit>>
    fun getFavoriteList(): Deferred<List<WeatherFavoriteEntity>>
    fun isFavorite(cityId: CityId): Deferred<Boolean>
}