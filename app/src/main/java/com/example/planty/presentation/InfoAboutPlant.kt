package com.example.planty.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun InfoAboutPlantScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier // TODO: Дополнить Modifier экрана информации о растении(настроить)
    ) { padding ->
        Text(
            text = "Это экран информации о растении",
            modifier = Modifier.padding(padding)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewInfoAboutPlantScreen() {
    InfoAboutPlantScreen()
}