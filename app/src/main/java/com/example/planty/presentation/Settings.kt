package com.example.planty.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

@Composable
fun SettingsScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier // TODO: Дополнить Modifier экрана настроек(настроить)
    ) { padding ->
        Text(modifier = Modifier.padding(padding), text = "Settings")
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSettingsScreen() {
    SettingsScreen()
}