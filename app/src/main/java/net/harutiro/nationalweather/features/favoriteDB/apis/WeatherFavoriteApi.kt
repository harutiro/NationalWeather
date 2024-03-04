package net.harutiro.nationalweather.features.favoriteDB.apis

import net.harutiro.nationalweather.Application
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity

class WeatherFavoriteApi {
    private val dao = Application.database.weatherFavoriteDao()

    fun insert(weatherFavoriteEntity: WeatherFavoriteEntity) {
        dao.insert(weatherFavoriteEntity)
    }

    fun update(weatherFavoriteEntity: WeatherFavoriteEntity) {
        dao.update(weatherFavoriteEntity)
    }

    fun delete(weatherFavoriteEntity: WeatherFavoriteEntity) {
        dao.delete(weatherFavoriteEntity)
    }

    fun getAll(): List<WeatherFavoriteEntity> {
        return dao.getAll()
    }

    fun getById(cityId: CityId): WeatherFavoriteEntity? {
        return dao.getByCityId(cityId)
    }
}