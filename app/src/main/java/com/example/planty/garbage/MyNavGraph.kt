package com.example.planty.garbage

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import com.example.planty.presentation.AddPlantScreen
import com.example.planty.presentation.MainScreen
import com.example.planty.presentation.NotificationsScreen
import com.example.planty.presentation.SettingsScreen

@Composable
fun MyNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.SettingsScreen.route) { SettingsScreen() }
        composable(Screen.MainScreen.route) { MainScreen(navController) }
        composable(Screen.NotificationsScreen.route) { NotificationsScreen() }
        composable(Screen.AddPlantScreen.route) { AddPlantScreen() }
    }
}