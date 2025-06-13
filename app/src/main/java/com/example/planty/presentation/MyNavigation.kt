package com.example.planty.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.planty.R

import com.example.planty.garbage.MyNavGraph
import com.example.planty.garbage.NavItemState
import com.example.planty.garbage.Screen


@Composable
fun MyNavigation() {
    val items = listOf(
        NavItemState(
            itemState = "main",
            icon = R.drawable.homebutton
        ),
        NavItemState(
            itemState = "settings",
            icon = R.drawable.settingsbutton
        ),
        NavItemState(
            itemState = "notifications",
            icon = R.drawable.alarm
        )
    )

    val navController = rememberNavController()

    var navBarState by rememberSaveable {
        mutableIntStateOf(
            0
        )
    }

    Scaffold(
        modifier = Modifier, // TODO: Дополнить Modifier главного экрана(настроить)
        topBar = {
            NavigationBar(
                containerColor = colorResource(R.color.bodily),
            ) {
                items.forEachIndexed { index, navItemState ->
                    NavigationBarItem(
                        colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = colorResource(R.color.black),
                            selectedIconColor = colorResource(R.color.black),
                            indicatorColor = colorResource(R.color.lightBrown)

                        ),
                        selected = navBarState == index,
                        onClick = {
                            navBarState = index
                            when (navBarState) {
                                0 -> {
                                    navController.navigate(Screen.MainScreen.route)
                                }

                                1 -> {
                                    navController.navigate(Screen.SettingsScreen.route)
                                }

                                2 -> {
                                    navController.navigate(Screen.NotificationsScreen.route)
                                }
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = navItemState.icon),
                                contentDescription = navItemState.itemState
                            )
                        },
                    )
                }
            }

        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            MyNavGraph(navController)
        }
    }
}