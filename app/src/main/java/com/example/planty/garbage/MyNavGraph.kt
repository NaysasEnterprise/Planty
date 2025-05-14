package com.example.planty.garbage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.planty.presentation.Main
import com.example.planty.presentation.SettingsScreen

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("settings") { SettingsScreen() }
        composable("main") { MainScreen() }
    }
}