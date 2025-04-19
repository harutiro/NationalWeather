package net.harutiro.nationalweather.features.weather.apis

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.harutiro.nationalweather.features.weather.entities.CityId
import net.harutiro.nationalweather.features.weather.entities.Weather
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class NationwideWeatherApiImpl : NationwideWeatherApi {
    // Timberを使う場合
    private val loggingInterceptor =
        HttpLoggingInterceptor {
            Timber.tag("OkHttp").d(it)
        }.apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    val client =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    val moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    val weatherService =
        Retrofit.Builder()
            .baseUrl("https://weather.tsukumijima.net")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(NationwideWeatherApiBuilderInterface::class.java)

    override suspend fun getNationwideWeather(cityId: CityId): Weather {
        try {
            val response = weatherService.getWeather(cityId.id)

            return if (response.isSuccessful) {
                Timber.tag("OkHttp").d(response.body().toString())
                val weather = response.body()
                if (weather != null) {
                    weather.cityId = cityId
                    weather
                } else {
                    Timber.tag("OkHttp").e("Response body is null")
                    Weather(listOf(), "データの取得に失敗しました", CityId.TOKYO)
                }
            } else {
                Timber.tag("OkHttp").e("API request failed: ${response.code()} - ${response.errorBody()?.string()}")
                Weather(listOf(), "API接続エラー: ${response.code()}", CityId.TOKYO)
            }
        } catch (e: Exception) {
            Timber.tag("OkHttp").e(e, "Network request failed")
            return Weather(listOf(), "ネットワークエラー: ${e.message}", CityId.TOKYO)
        }
    }
}
