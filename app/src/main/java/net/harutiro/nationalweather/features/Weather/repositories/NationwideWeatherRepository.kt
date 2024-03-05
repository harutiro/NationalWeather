package net.harutiro.nationalweather.features.Weather.repositories

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.Job
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather

interface NationwideWeatherRepository {
    suspend fun getNationwideWeather(weathers: SnapshotStateList<Weather>) : Job
    suspend fun getPrefectureWeather(city : CityId) : Weather
}