package net.harutiro.nationalweather.core.presenter.favorite.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import net.harutiro.nationalweather.core.presenter.widget.BookmarkButton
import net.harutiro.nationalweather.core.utils.DateUtils
import net.harutiro.nationalweather.core.widgets.Center
import net.harutiro.nationalweather.features.Weather.entities.Forecast
import net.harutiro.nationalweather.features.Weather.entities.Max
import net.harutiro.nationalweather.features.Weather.entities.Min
import net.harutiro.nationalweather.features.Weather.entities.Temperature
import net.harutiro.nationalweather.features.Weather.entities.Weather

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrefectureFavoriteWeatherCell(
    modifier: Modifier = Modifier,
    weather:Weather,
    isFavorite: Boolean,
    favoriteOnClick: () -> Unit,
){
    ElevatedCard (
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .wrapContentHeight(),
        content = {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentSize()
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = weather.title,
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 30.sp,
                                textAlign = TextAlign.Center,
                            )
                        )
                    },
                    actions = {
                        BookmarkButton(
                            isBookmark = isFavorite,
                        ) {
                            favoriteOnClick()
                        }
                    },
                )

                for(dayWeather in weather.forecasts){
                    FavoriteWeatherCell(
                        imageUrl = dayWeather.image.url,
                        tempMax = dayWeather.temperature.max.celsius ?: 0.0,
                        tempMin = dayWeather.temperature.min.celsius ?: 0.0,
                        date = DateUtils.apiDateToJapaneseNotation(dayWeather.date),
                    )
                }
            }
        },
    )
}

@Composable
fun FavoriteWeatherCell(
    imageUrl: String,
    tempMax: Double,
    tempMin: Double,
    date: String,
) {
    Box(
        // 最小の横幅と縦幅を指定する。
        modifier = Modifier
            .background(CardDefaults.cardColors().containerColor)
            .fillMaxWidth()
            .height(128.dp)
        ) {
        Center {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                // Image.
                WeatherImage(
                    imageUrl = imageUrl,
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .shadow(1.dp)
                )

                Column (
                    verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                    horizontalAlignment = Alignment.End,
                ){
                    // City name.
                    WeatherDate(
                        date = date,
                    )
                    // Temperature.
                    WeatherTemperature(
                        tempMax = tempMax,
                        tempMin = tempMin,
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherImage(
    imageUrl: String,
    modifier: Modifier,
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(imageUrl, imageLoader = imageLoader),
        contentDescription = null,
        modifier = modifier,
    )
}

@Composable
fun WeatherTemperature(
    tempMax: Double,
    tempMin: Double,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(11.dp, Alignment.Start),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = "↑$tempMax",
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 22.5.sp,
                color = Color(0xFFFF6969),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = "↓$tempMin",
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 22.5.sp,
                color = Color(0xFF2697FF),
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun WeatherDate(
    date: String,
) {
    Text(
        text = date,
        style = TextStyle(
            fontSize = 25.sp,
            lineHeight = 22.5.sp,
            textAlign = TextAlign.Center,
        )
    )
}


@Preview
@Composable
fun PreviewNationwideWeatherCell() {
    // 横幅と縦幅を指定してプレビューを表示する。
    FavoriteWeatherCell(
        imageUrl = "https://www.jma.go.jp/bosai/forecast/img/100.svg",
        tempMax = 30.0,
        tempMin = 20.0,
        date = "10月23日(水)",
    )
}

@Preview
@Composable
fun PreviewPrefectureFavoriteWeatherCell() {
    // 横幅と縦幅を指定してプレビューを表示する。

    val weather = Weather(
        title = "東京都",
        forecasts = listOf(
            Forecast(
                date = "2021-10-23",
                image = net.harutiro.nationalweather.features.Weather.entities.Image(
                    title = "晴れ",
                    url = "https://www.jma.go.jp/bosai/forecast/img/100.svg",
                ),
                temperature = Temperature(
                    max = Max(
                        celsius = 30.0,
                        fahrenheit = null
                    ),
                    min = Min(
                        celsius = 20.0,
                        fahrenheit = null
                    ),
                ),
                telop = "晴れ",
            ),
            Forecast(
                date = "2021-10-24",
                image = net.harutiro.nationalweather.features.Weather.entities.Image(
                    title = "晴れ",
                    url = "https://www.jma.go.jp/bosai/forecast/img/100.svg",
                ),
                temperature = Temperature(
                    max = Max(
                        celsius = 30.0,
                        fahrenheit = null
                    ),
                    min = Min(
                        celsius = 20.0,
                        fahrenheit = null
                    ),
                ),
                telop = "晴れ",
            ),
            Forecast(
                date = "2021-10-25",
                image = net.harutiro.nationalweather.features.Weather.entities.Image(
                    title = "晴れ",
                    url = "https://www.jma.go.jp/bosai/forecast/img/100.svg",
                ),
                temperature = Temperature(
                    max = Max(
                        celsius = 30.0,
                        fahrenheit = null
                    ),
                    min = Min(
                        celsius = 20.0,
                        fahrenheit = null
                    ),
                ),
                telop = "晴れ",
            ),
        ),
        cityId = null,
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding( all = 8.dp)
    ) {
        items(3) {
            PrefectureFavoriteWeatherCell(
                weather = weather,
                isFavorite = true,
            ) {}
        }
    }
}
