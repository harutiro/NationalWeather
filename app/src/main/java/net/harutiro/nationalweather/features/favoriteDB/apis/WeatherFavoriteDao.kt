package net.harutiro.nationalweather.features.favoriteDB.apis

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import net.harutiro.nationalweather.features.Weather.entities.CityId
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntity
import net.harutiro.nationalweather.features.favoriteDB.entities.WeatherFavoriteEntityConst

@Dao
interface WeatherFavoriteDao {
    @Insert
    fun insert(weatherFavoriteEntity: WeatherFavoriteEntity)

    @Update
    fun update(weatherFavoriteEntity: WeatherFavoriteEntity)

    @Delete
    fun delete(weatherFavoriteEntity: WeatherFavoriteEntity)

    @Query("SELECT * FROM ${WeatherFavoriteEntityConst.TABLE_NAME}")
    fun getAll(): List<WeatherFavoriteEntity>

    @Query("SELECT * FROM ${WeatherFavoriteEntityConst.TABLE_NAME} WHERE CityId = :cityId LIMIT 1")
    fun getByCityId(cityId: CityId): WeatherFavoriteEntity?

}
