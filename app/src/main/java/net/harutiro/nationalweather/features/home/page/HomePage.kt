package net.harutiro.nationalweather.features.home.page

import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.features.home.repositories.NationwideWeatherRepository
import net.harutiro.nationalweather.features.home.viewModel.HomeViewModel

@Composable
fun HomePage(viewModel: HomeViewModel = viewModel()) {
    val scope = rememberCoroutineScope()

    scope.launch {
        val nationwideWeatherRepository = NationwideWeatherRepository()
        val weathers = nationwideWeatherRepository.getNationwideWeather()

        viewModel.weathers.addAll(weathers)

        Log.d("HomePage",weathers.toString())
    }

    Column {
        viewModel.weathers.forEach {
            NationwideWeatherCell(
                imageUrl = it.forecasts[0].image.url,
                tempMax = it.forecasts[0].temperature.max.celsius,
                tempMin = it.forecasts[0].temperature.max.celsius,
                cityName = it.title)
        }


    }
}