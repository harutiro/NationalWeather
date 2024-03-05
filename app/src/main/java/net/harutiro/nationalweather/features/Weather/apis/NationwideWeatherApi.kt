package net.harutiro.nationalweather.features.Weather.apis

import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather

interface NationwideWeatherApi {
    suspend fun getNationwideWeather(cityId: CityId): Weather
}