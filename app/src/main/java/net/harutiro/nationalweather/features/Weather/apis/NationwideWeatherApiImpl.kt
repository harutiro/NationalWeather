package net.harutiro.nationalweather.features.Weather.apis

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class NationwideWeatherApiImpl: NationwideWeatherApi{
    override suspend fun getNationwideWeather(cityId:CityId): Weather {
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
            .create(NationwideWeatherApiBuilderInterface::class.java)

        val response = weatherService.getWeather(cityId.id)

        return if(response.isSuccessful) {
            Timber.tag("OkHttp").d(response.body().toString())
            val weather = response.body()
            weather?.cityId = cityId
            weather ?: Weather(listOf(),"", CityId.tokyo)
        } else {
            Timber.tag("OkHttp").d(response.errorBody().toString())
            Weather(listOf(),"" , CityId.tokyo)
        }
    }
}