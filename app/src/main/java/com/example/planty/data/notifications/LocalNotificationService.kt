package com.example.planty.data.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.planty.R
import com.example.planty.application.App
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalNotificationService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(
        plantName: String,
    ) {
        val notification = NotificationCompat.Builder(
            context,
            App.CHANNEL_WATERING_REMINDER_ID
        )
            .setSmallIcon(R.drawable.flower)
            .setContentTitle("Planty")
            .setContentText("Я - $plantName и меня нужно полить!")
            .build()
        notificationManager.notify(
            plantName.hashCode(),
            notification
        )
    }
}