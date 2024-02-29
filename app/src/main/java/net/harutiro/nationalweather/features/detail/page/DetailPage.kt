package net.harutiro.nationalweather.features.detail.page

import androidx.compose.foundation.layout.Arrangement
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.detail.viewModel.DetailViewModel
import net.harutiro.nationalweather.features.home.page.NationwideWeatherCell
import net.harutiro.nationalweather.features.home.viewModel.HomeViewModel
import java.lang.Double.NaN

@Composable
fun DetailPage(toBottomNavigationBar: () -> Unit, cityId: CityId , viewModel: DetailViewModel = viewModel()) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(all = 8.dp)
    ) {
        items(viewModel.weather.value?.forecasts ?: emptyList()) {
            DetailWeatherCell(
                imageUrl = it.image.url,
                tempMax = it.temperature.max.celsius ?: NaN,
                tempMin = it.temperature.min.celsius ?: NaN,
                date = it.date,
            )
        }
    }

    DetailLifecycleEvent()
}

@Composable
fun DetailLifecycleEvent(
    viewModel: HomeViewModel = viewModel(),
) {
    DetailObserveLifecycleEvent { event ->
        // 検出したイベントに応じた処理を実装する。
        when (event) {
            Lifecycle.Event.ON_CREATE -> viewModel.getWeather()
            else -> {}
        }
    }
}

@Composable
fun DetailObserveLifecycleEvent(onEvent: (Lifecycle.Event) -> Unit = {}) {
    // Safely update the current lambdas when a new one is provided
    val currentOnEvent by rememberUpdatedState(onEvent)
    val lifecycleOwner = LocalLifecycleOwner.current

    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _, event ->
            currentOnEvent(event)
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}