package net.harutiro.nationalweather.features.Weather.apis

import net.harutiro.nationalweather.features.Weather.entities.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NationwideWeatherApiBuilderInterface {
    @GET("/api/forecast/city/{city_id}")
    suspend fun getWeather(
        @Path("city_id") cityId: String,
    ): Response<Weather>
}