package net.harutiro.nationalweather.features.home.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import net.harutiro.nationalweather.core.router.MainRoute
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.Weather.entities.Weather
import net.harutiro.nationalweather.features.home.viewModel.HomeViewModel
import java.lang.Double.NaN

@Composable
fun HomePage(toDetail: (cityId: CityId) -> Unit ,viewModel: HomeViewModel = viewModel()) {

    LaunchedEffect(Unit){
        viewModel.getWeather()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(all = 8.dp)
    ) {
        items(viewModel.weathers) {
            NationwideWeatherCell(
                imageUrl = it.forecasts[0].image.url,
                tempMax = it.forecasts[0].temperature.max.celsius ?: NaN,
                tempMin = it.forecasts[0].temperature.min.celsius ?: NaN,
                cityName = Weather.getCityAcquisition(it.title),
                goDetail = {
                    Log.d("HomePage", "cityId: ${it.cityId}")
                    toDetail(it.cityId ?:CityId.tokyo)
                }
            )
        }
    }
}
