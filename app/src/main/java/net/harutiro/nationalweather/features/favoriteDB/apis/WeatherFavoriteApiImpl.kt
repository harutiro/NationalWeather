package net.harutiro.nationalweather.features.favoriteDB.apis

import net.harutiro.nationalweather.Application
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity

class WeatherFavoriteApiImpl : WeatherFavoriteApi {
    private val dao = Application.database.weatherFavoriteDao()

    override fun insert(weatherFavoriteEntity: WeatherFavoriteEntity) {
        dao.insert(weatherFavoriteEntity)
    }

    override fun update(weatherFavoriteEntity: WeatherFavoriteEntity) {
        dao.update(weatherFavoriteEntity)
    }

    override fun delete(weatherFavoriteEntity: WeatherFavoriteEntity) {
        dao.delete(weatherFavoriteEntity)
    }

    override fun getAll(): List<WeatherFavoriteEntity> {
        return dao.getAll()
    }

    override fun getById(cityId: CityId): WeatherFavoriteEntity? {
        return dao.getByCityId(cityId)
    }
}