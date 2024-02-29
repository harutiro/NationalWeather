package net.harutiro.nationalweather

import android.app.Application
import android.content.res.Configuration
import androidx.room.Room
import net.harutiro.nationalweather.core.utils.room.AppDatabase

class Application: Application() {

    // デバイス構成が変更されたとき
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // AppDatabaseをビルドする
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}