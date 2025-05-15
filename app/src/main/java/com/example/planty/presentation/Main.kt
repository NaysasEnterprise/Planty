package com.example.planty.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.planty.R

import com.example.planty.garbage.MyNavGraph
import com.example.planty.garbage.NavItemState


@Composable
fun Main() {
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
            icon = R.drawable.baseline_notifications_24
        )
    )

    var navBarState by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier, // TODO: Дополнить Modifier главного экрана(настроить)
        topBar = {
            NavigationBar {
                items.forEachIndexed { index, navItemState ->
                    NavigationBarItem(
                        selected = navBarState == index,
                        onClick = {
                            navBarState = index
                            if (navBarState == 0) {
                                navController.navigate("main")
                            } else if (
                                navBarState == 1) {
                                navController.navigate("settings")
                            } else if (
                                navBarState == 2
                            ) {
                                navController.navigate("notifications")
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