package net.harutiro.nationalweather.core.utils.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.harutiro.nationalweather.core.utils.room.typeConverter.CityIdConverter
import net.harutiro.nationalweather.core.utils.room.typeConverter.DateConverter
import net.harutiro.nationalweather.features.favoriteDB.apis.WeatherFavoriteDao
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity

@Database(entities = [WeatherFavoriteEntity::class], version = 1)
@TypeConverters(CityIdConverter::class,DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherFavoriteDao(): WeatherFavoriteDao
}