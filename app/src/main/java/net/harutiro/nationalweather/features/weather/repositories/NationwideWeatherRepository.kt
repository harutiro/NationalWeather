package net.harutiro.nationalweather.features.weather.repositories

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.Job
import net.harutiro.nationalweather.features.weather.entities.CityId
import net.harutiro.nationalweather.features.weather.entities.Weather

interface NationwideWeatherRepository {
    suspend fun getNationwideWeather(weathers: SnapshotStateList<Weather>): Job

    suspend fun getPrefectureWeather(city: CityId): Weather
}
