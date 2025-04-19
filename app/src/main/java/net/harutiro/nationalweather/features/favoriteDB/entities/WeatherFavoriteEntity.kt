package net.harutiro.nationalweather.features.favoriteDB.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.harutiro.nationalweather.features.weather.entities.CityId
import java.util.Date

@Entity(tableName = WeatherFavoriteEntity.TABLE_NAME)
data class WeatherFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var cityId: CityId,
    var createAt: Date,
    var updateAt: Date,
) {
    companion object {
        const val TABLE_NAME = "weather_favorite_entity"
    }
}
