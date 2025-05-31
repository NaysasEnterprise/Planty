package com.example.planty.data.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.planty.R
import com.example.planty.application.App
import com.example.planty.entity.Plant
import com.example.planty.presentation.MainActivity

object NotificationServiceLocal {

    private const val NOTIFICATION_ID = 1001
    private const val REQUEST_CODE = 2002

    fun showWateringNotification(context: Context, plant: Plant) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("plant_Id", plant.id) // Updated to Long
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(
            context,
            App.CHANNEL_WATERING_REMINDER_ID
        )
            .setSmallIcon(R.drawable.flower)
            .setContentTitle("Пора поливать ${plant.name}")
            .setContentText("${plant.species ?: "Растение"} требует полива")
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        notificationManager.notify((NOTIFICATION_ID + plant.id).toInt(), notification)
    }

    fun cancelNotification(context: Context, plantId: Long) { // Updated to Long
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        notificationManager.cancel((NOTIFICATION_ID + plantId).toInt())
    }
}