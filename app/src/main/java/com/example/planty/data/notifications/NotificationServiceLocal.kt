package com.example.planty.data.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.planty.R
import com.example.planty.application.App

object NotificationServiceLocal {
    fun showNotification(context: Context) {
        val notification = NotificationCompat.Builder(
            context, App.CHANNEL_NOTIFICATION_ID
        )
            .setSmallIcon(R.drawable.flower)
            .setContentTitle("тестовое")
            .setContentText("Меня типа надо полить")
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .build()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1,notification)
    }
}