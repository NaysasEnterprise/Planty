package com.example.planty.application

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.room.Room
import com.example.planty.data.PlantDatabase
import dagger.hilt.android.HiltAndroidApp
import java.util.Calendar

@HiltAndroidApp
class App : Application() {
    lateinit var db: PlantDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            PlantDatabase::class.java,
            "db"
        ).build()

        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_WATERING_REMINDER_ID,
                "Напоминания о поливе",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Уведомления о необходимости полива растений"
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_WATERING_REMINDER_ID = "water_reminder"
        const val CHANNEL_NOTIFICATION_ID = "channel_notification_id"
    }
}