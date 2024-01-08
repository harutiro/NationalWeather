package net.harutiro.nationalweather.features.home.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import net.harutiro.nationalweather.features.home.api.NationwideWeatherApiImpl

@Composable
fun NationwideWeatherCell(
    imageUrl: String,
    tempMax: Double,
    tempMin: Double,
    cityName: String,
) {
    Card(
        modifier = Modifier
            .width(133.dp)
            .height(172.dp),
    ) {
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

@Composable
fun NationwideWeatherImage(
    imageUrl: String,
    modifier: Modifier,
) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
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
    NationwideWeatherCell(
        imageUrl = "https://www.jma.go.jp/bosai/forecast/img/100.svg",
        tempMax = 10.0,
        tempMin = 0.0,
        cityName = "Tokyo",
    )
}
