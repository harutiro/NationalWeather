package net.harutiro.nationalweather.features.Weather.repositories

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.features.Weather.apis.NationwideWeatherApiImpl
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather

class NationwideWeatherRepository {
    @OptIn(DelicateCoroutinesApi::class)
    suspend fun getNationwideWeather(weathers:SnapshotStateList<Weather>): Job {
        // 並列処理で取得
        return GlobalScope.launch {
            val jobList = mutableListOf<Deferred<Weather>>()
            for(i in CityId.entries) {
                jobList.add(
                    async { NationwideWeatherApiImpl.getNationwideWeather(i) }
                )
            }
            val getWeather = jobList.awaitAll()
            weathers.addAll(getWeather)
        }
    }

    suspend fun getPrefectureWeather(city :CityId) : Weather {
        return NationwideWeatherApiImpl.getNationwideWeather(city)
    }
}