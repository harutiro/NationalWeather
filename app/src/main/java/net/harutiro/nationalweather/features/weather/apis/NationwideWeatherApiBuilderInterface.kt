package net.harutiro.nationalweather.features.weather.apis

import net.harutiro.nationalweather.features.weather.entities.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NationwideWeatherApiBuilderInterface {
    @GET("/api/forecast/city/{city_id}")
    suspend fun getWeather(
        @Path("city_id") cityId: String,
    ): Response<Weather>
}
