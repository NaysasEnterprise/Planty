package com.example.planty.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun LoadScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier // TODO: Дополнить Modifier экрана загрузки(настроить)
    ) { padding ->
        Text(
            text = "Это главный экран",
            modifier = Modifier.padding(padding)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewLoadScreen() {
    LoadScreen()
}