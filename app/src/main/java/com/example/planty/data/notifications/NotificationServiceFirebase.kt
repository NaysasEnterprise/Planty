package com.example.planty.data.notifications

import com.google.firebase.messaging.FirebaseMessagingService

class NotificationServiceFirebase : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }
}

