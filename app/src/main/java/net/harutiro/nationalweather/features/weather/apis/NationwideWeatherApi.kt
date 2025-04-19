package net.harutiro.nationalweather.features.weather.apis

import net.harutiro.nationalweather.features.weather.entities.CityId
import net.harutiro.nationalweather.features.weather.entities.Weather

interface NationwideWeatherApi {
    suspend fun getNationwideWeather(cityId: CityId): Weather
}
