package net.harutiro.nationalweather.core.presenter.detail.page

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.core.entities.TopAppBarState
import net.harutiro.nationalweather.core.presenter.detail.viewModel.DetailViewModel
import net.harutiro.nationalweather.core.presenter.widget.BookmarkButton
import net.harutiro.nationalweather.core.utils.DateUtils
import net.harutiro.nationalweather.core.widgets.ArrowBackButton
import net.harutiro.nationalweather.features.weather.entities.CityId
import net.harutiro.nationalweather.features.weather.entities.Weather
import java.lang.Double.NaN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPage(
    toBackPage: () -> Unit,
    cityId: CityId,
    viewModel: DetailViewModel = viewModel(),
    topAppBarChanged: (content: @Composable () -> Unit) -> Unit,
) {
    // スナックバーの表示
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    viewModel.city.value = cityId

    Log.d("DetailPage", "cityId: $cityId")

    LaunchedEffect(key1 = viewModel.city) {
        viewModel.getWeather(cityId)
    }

    val topAppBarState =
        remember {
            mutableStateOf(
                TopAppBarState(
                    title = "詳細ページ",
                    showBackButton = true,
                    onBackClick = { toBackPage() },
                ),
            )
        }

    topAppBarChanged {
        TopAppBar(
            navigationIcon = {
                ArrowBackButton {
                    toBackPage()
                }
            },
            title = {
                val cityName = Weather.getCityAcquisition(viewModel.weather.value?.title ?: "")
                Text(text = "${cityName}の3日間の天気")
            },
            actions = {
                BookmarkButton(
                    isBookmark = viewModel.bookmark.value,
                ) {
                    viewModel.updateBookmark {
                        scope.launch {
                            // スナックバーが表示された後にスナックバーが呼ばれたら前のスナックバーをキャンセルする
                            hostState.currentSnackbarData?.dismiss()
                            hostState.showSnackbar(it)
                        }
                    }
                }
            },
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(viewModel.weather.value?.forecasts ?: emptyList()) {
            DetailWeatherCell(
                imageUrl = it.image.url,
                tempMax = it.temperature.max.celsius ?: NaN,
                tempMin = it.temperature.min.celsius ?: NaN,
                date = DateUtils.apiDateToJapaneseNotation(it.date),
            )
        }
    }
}
