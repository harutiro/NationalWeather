package net.harutiro.nationalweather.features.home.api

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.harutiro.nationalweather.features.home.entities.Weather
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class NationwideWeatherApiImpl {
    companion object{
        suspend fun getNationwideWeather(cityId:String) : Weather {
            // Timberを使う場合
            val logging = HttpLoggingInterceptor {
                Timber.tag("OkHttp").d(it)
            }
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val weatherService = Retrofit.Builder()
                .baseUrl("https://weather.tsukumijima.net")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(NationwideWeatherApi::class.java)

            val response = weatherService.getWeather(cityId)

            if(response.isSuccessful) {
                Timber.tag("OkHttp").d(response.body().toString())
                return response.body() ?: Weather(listOf(),"")
            } else {
                Timber.tag("OkHttp").d(response.errorBody().toString())
                return Weather(listOf(),"")
            }
        }
    }
}