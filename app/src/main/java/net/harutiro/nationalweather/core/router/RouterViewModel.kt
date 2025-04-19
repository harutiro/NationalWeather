package net.harutiro.nationalweather.core.router

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class RouterViewModel : ViewModel() {
    var selectedItemIndex = mutableIntStateOf(0)
    var isStarted = mutableStateOf(false)
}
