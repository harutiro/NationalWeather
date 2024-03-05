package net.harutiro.nationalweather.features.favoriteDB.apis

import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity

interface WeatherFavoriteApi {
    fun insert(weatherFavoriteEntity: WeatherFavoriteEntity)
    fun update(weatherFavoriteEntity: WeatherFavoriteEntity)
    fun delete(weatherFavoriteEntity: WeatherFavoriteEntity)
    fun getAll(): List<WeatherFavoriteEntity>
    fun getById(cityId: CityId): WeatherFavoriteEntity?
}