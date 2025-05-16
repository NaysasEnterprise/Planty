package com.example.planty.application

import android.app.Application
import androidx.room.Room
import com.example.planty.data.PlantDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    lateinit var db: PlantDatabase
    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            PlantDatabase::class.java,
            "db"
        ).build()
    }
}