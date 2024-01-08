package net.harutiro.nationalweather.features.home.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import net.harutiro.nationalweather.features.home.entities.Weather

class HomeViewModel : ViewModel() {
    val weathers = mutableStateListOf<Weather>()
}