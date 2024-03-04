package net.harutiro.nationalweather.core.presenter.favorite.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.harutiro.nationalweather.R
import net.harutiro.nationalweather.core.presenter.favorite.viewModel.FavoriteViewModel
import net.harutiro.nationalweather.core.presenter.widget.LoadingPage
import net.harutiro.nationalweather.features.Weather.entities.CityId

@OptIn(DelicateCoroutinesApi::class, ExperimentalFoundationApi::class)
@Composable
fun FavoritePage(viewModel: FavoriteViewModel = viewModel()) {

    // スナックバーの表示
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getFavoriteAll()
    }

    Scaffold (
        snackbarHost = { SnackbarHost(hostState) },
    ){ padding ->
        LoadingPage(
            isLoading = viewModel.isLoading.value,
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding( padding )
            ) {
                items(
                    viewModel.weatherList.toList(),
                    key = { it.cityId?.id ?: CityId.tokyo.id }
                ) {
                    PrefectureFavoriteWeatherCell(
                        modifier = Modifier.animateItemPlacement(),
                        weather = it,
                        isFavorite = viewModel.checkFavorite(it.cityId ?: CityId.tokyo),
                        favoriteOnClick = {
                            GlobalScope.launch {
                                viewModel.updateBookmark(cityId = it.cityId ?: CityId.tokyo) {
                                    scope.launch {
                                        // スナックバーが表示された後にスナックバーが呼ばれたら前のスナックバーをキャンセルする
                                        hostState.currentSnackbarData?.dismiss()
                                        hostState.showSnackbar(it)
                                    }
                                }
                            }
                        }
                    )
                }
            }

            if (viewModel.weatherList.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.empty_text),
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        text ="(人；´Д｀)ｺﾞﾒﾝﾈ"
                    )
                }
            }
        }
    }
}