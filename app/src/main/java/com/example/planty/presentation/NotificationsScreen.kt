package com.example.planty.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotificationsScreen() {
    Scaffold(
        modifier = Modifier // TODO: Дополнить Modifier экрана уведомлений(настроить)
    ) { padding ->
        Text(modifier = Modifier.padding(padding), text = "Notifications")
    }
}