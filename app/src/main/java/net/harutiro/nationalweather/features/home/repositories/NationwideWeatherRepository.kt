package net.harutiro.nationalweather.features.home.repositories

import net.harutiro.nationalweather.features.home.api.NationwideWeatherApiImpl
import net.harutiro.nationalweather.features.home.entities.CityId
import net.harutiro.nationalweather.features.home.entities.Weather

class NationwideWeatherRepository {
    suspend fun getNationwideWeather(): List<Weather> {

        val weathers = mutableListOf<Weather>()
        for(i in CityId.values()) {
            weathers.add(NationwideWeatherApiImpl.getNationwideWeather(i.id))
        }
        return weathers
    }
}