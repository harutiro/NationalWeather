package net.harutiro.nationalweather.features.home.api

import net.harutiro.nationalweather.features.home.entities.Weather
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface NationwideWeatherApi {
    @GET("/api/forecast/city/{city_id}")
    suspend fun getWeather(
        @Path("city_id") cityId: String,
    ): Response<Weather>
}