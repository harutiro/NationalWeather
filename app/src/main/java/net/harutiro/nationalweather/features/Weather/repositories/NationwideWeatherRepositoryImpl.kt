package net.harutiro.nationalweather.features.Weather.repositories

import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.features.Weather.apis.NationwideWeatherApi
import net.harutiro.nationalweather.features.Weather.apis.NationwideWeatherApiImpl
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather

class NationwideWeatherRepositoryImpl(
    private val nationwideWeatherApi: NationwideWeatherApi = NationwideWeatherApiImpl()
): NationwideWeatherRepository {
    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun getNationwideWeather(weathers:SnapshotStateList<Weather>): Job {
        // 並列処理で取得
        return GlobalScope.launch {
            val jobList = mutableListOf<Deferred<Weather>>()
            for(i in CityId.entries) {
                jobList.add(
                    async { nationwideWeatherApi.getNationwideWeather(i) }
                )
            }
            val getWeather = jobList.awaitAll()
            weathers.addAll(getWeather)
        }
    }

    override suspend fun getPrefectureWeather(city :CityId) : Weather {
        return nationwideWeatherApi.getNationwideWeather(city)
    }
}