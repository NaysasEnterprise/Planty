package com.example.planty.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
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

        createNotificationChannel("Полив растений")
    }

    fun createNotificationChannel(name: String?) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_NOTIFICATION_ID,
                name,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Тестовое уведомление!"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        val CHANNEL_NOTIFICATION_ID = "channel_notification_id"

    }
}