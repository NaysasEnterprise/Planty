package com.example.planty.garbage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.planty.presentation.AddPlantScreen
import com.example.planty.presentation.MainScreen
import com.example.planty.presentation.NotificationsScreen
import com.example.planty.presentation.SettingsScreen

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("settings") { SettingsScreen() }
        composable("main") { MainScreen() }
        composable("notifications") { NotificationsScreen() }
        composable("addPlant") { AddPlantScreen() }
    }
}