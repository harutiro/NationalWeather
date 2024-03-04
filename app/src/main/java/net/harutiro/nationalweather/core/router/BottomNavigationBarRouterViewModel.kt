package net.harutiro.nationalweather.core.router

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BottomNavigationBarRouterViewModel: ViewModel()  {
    var selectedItemIndex = mutableIntStateOf(0)
    var isStarted = mutableStateOf(false)
}