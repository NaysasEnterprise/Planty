package com.example.planty.garbage

sealed class Screen(val route: String) {
    data object MainScreen: Screen(route = "main")
    data object SettingsScreen: Screen(route = "settings")
    data object NotificationsScreen: Screen(route = "notifications")
    data object AddPlantScreen: Screen(route = "addPlant")
    data object InfoAboutPlantScreen: Screen(route = "infoPlant")
}