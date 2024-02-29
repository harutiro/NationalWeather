package net.harutiro.nationalweather.core.presenter.home.page

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
import net.harutiro.nationalweather.core.widgets.Center
import timber.log.Timber

@Composable
fun NationwideWeatherCell(
    imageUrl: String,
    tempMax: Double,
    tempMin: Double,
    cityName: String,
    goDetail: () -> Unit,
) {
    Card(
        onClick = {
            // ここでDetail画面に遷移する処理を実装する
            goDetail()
        },
        modifier = Modifier
            .width(72.dp)
            .height(172.dp),

    ) {
        Center {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Image.
                NationwideWeatherImage(
                    imageUrl = imageUrl,
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp)
                        .shadow(1.dp)
                )
                // Temperature.
                NationwideWeatherTemperature(
                    tempMax = tempMax,
                    tempMin = tempMin,
                )
                // City name.
                NationwideWeatherCityName(
                    cityName = cityName,
                )
            }
        }
    }
}

@Composable
fun NationwideWeatherImage(
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
fun NationwideWeatherTemperature(
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
fun NationwideWeatherCityName(
    cityName: String,
) {
    Text(text = cityName)
}


@Preview
@Composable
fun PreviewNationwideWeatherCell() {
    // 横幅と縦幅を指定してプレビューを表示する。
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(all = 8.dp)
    ) {
        items(4) {
            NationwideWeatherCell(
                imageUrl = "https://www.jma.go.jp/bosai/forecast/img/100.svg",
                tempMax = 30.0,
                tempMin = 20.0,
                cityName = "東京都",
                goDetail = {
                    Timber.tag("NationwideWeatherCell").d("goDetail")
                }
            )
        }
    }
}
