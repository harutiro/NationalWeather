package net.harutiro.nationalweather.features.detail.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.detail.viewModel.DetailViewModel
import net.harutiro.nationalweather.features.home.page.NationwideWeatherCell
import net.harutiro.nationalweather.features.home.viewModel.HomeViewModel
import java.lang.Double.NaN

@Composable
fun DetailPage(toBottomNavigationBar: () -> Unit, cityId: CityId , viewModel: DetailViewModel = viewModel()) {

    viewModel.city.value = cityId

    Log.d("DetailPage", "cityId: $cityId")

    LaunchedEffect(key1 = viewModel.city) {
        viewModel.getWeather(cityId)
    }

//    Button(
//        onClick = {
//            toBottomNavigationBar()
//        }
//    ){
//        Text("BottomNavigationBarへ")
//    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(all = 16.dp)
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
}
