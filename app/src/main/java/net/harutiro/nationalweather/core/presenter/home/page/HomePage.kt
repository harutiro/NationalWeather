package net.harutiro.nationalweather.core.presenter.home.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
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
import net.harutiro.nationalweather.core.presenter.home.viewModel.HomeViewModel
import net.harutiro.nationalweather.core.presenter.widget.LoadingPage
import java.lang.Double.NaN

@Composable
fun HomePage(toDetail: (cityId: CityId) -> Unit ,viewModel: HomeViewModel = viewModel()) {

    LaunchedEffect(key1 = viewModel.weathers){
        viewModel.getWeather()
    }

    Scaffold { padding ->
        LoadingPage(
            isLoading = viewModel.isLoading.value,
            content = {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(padding)
                ) {
                    items(viewModel.weathers, key = { it.cityId?.id ?: CityId.tokyo.id }) {
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
        )
    }
}
