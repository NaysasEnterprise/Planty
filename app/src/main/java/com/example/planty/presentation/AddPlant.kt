package com.example.planty.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun AddPlantScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier // TODO: Дополнить Modifier экрана добавления растения (настроить)
    ) { padding ->
        Text(
            text = "Это экран, на котором можно добавить растение",
            modifier = Modifier.padding(padding)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewAddPlantScreen() {
    AddPlantScreen()
}