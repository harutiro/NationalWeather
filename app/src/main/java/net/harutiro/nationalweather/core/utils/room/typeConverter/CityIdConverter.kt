package net.harutiro.nationalweather.core.utils.room.typeConverter

import androidx.room.TypeConverter
import net.harutiro.nationalweather.features.weather.entities.CityId

class CityIdConverter {
    @TypeConverter
    fun fromCityId(cityId: CityId): String {
        return cityId.id
    }

    @TypeConverter
    fun toCityId(value: String): CityId? {
        return CityId.idToCityId(value)
    }
}
