package net.harutiro.nationalweather.features.Weather.repositories

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import net.harutiro.nationalweather.features.Weather.api.NationwideWeatherApiImpl
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather

class NationwideWeatherRepository {
    suspend fun getNationwideWeather(weathers:SnapshotStateList<Weather>) {
        for(i in CityId.values()) {
            Log.d("NationwideWeatherRepository",i.id + i.name + "を取得")
            weathers.add(NationwideWeatherApiImpl.getNationwideWeather(i))
        }
    }

    suspend fun getPrefectureWeather(city :CityId) : Weather {
        return NationwideWeatherApiImpl.getNationwideWeather(city)
    }
}