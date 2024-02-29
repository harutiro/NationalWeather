package net.harutiro.nationalweather.features.Weather.entities

data class Weather(
    var forecasts: List<Forecast>,
    var title: String,
)

data class Forecast(
    var date: String,
    var telop: String,
    var temperature: Temperature,
    var image: Image,
)

data class Image(
    var url: String,
    var title: String,
)
data class Temperature(
    var min: Min,
    var max: Max,
)

data class Min(
    var celsius: Double?,
    var fahrenheit: Double?,
)

data class Max(
    var celsius: Double?,
    var fahrenheit: Double?,
)
