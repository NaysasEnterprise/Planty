package com.example.planty.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.planty.R
import com.example.planty.entity.Plant
import com.example.planty.garbage.Screen
import com.example.planty.ui.theme.PlantyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: PlantViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val plants by viewModel.plants.collectAsState(initial = emptyList())
    val rememberWidth = LocalConfiguration.current.screenWidthDp.dp
    PlantyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.beige))
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.my_plants),
                style = TextStyle(
                    fontSize = 64.sp,
                    color = colorResource(R.color.darkBrown),
                    fontWeight = FontWeight.Bold
                )
            )
            LazyRow(
            ) {
                items(plants) { plant ->
                    plant.name?.let {
                        PlantItem(it, navController, plant)
                    }
                }
            }
            Column(
            ) {
                Text(
                    text = stringResource(R.string.need_water),
                    style = TextStyle(
                        fontSize = 32.sp,
                        color = colorResource(R.color.darkBrown),
                        fontWeight = FontWeight.Medium
                    ),
                )
                PlantsToWaterScreen(viewModel, navController = navController)
            }


            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(rememberWidth * 0.7f)
                        .clip(RoundedCornerShape(25.dp))
                        .background(colorResource(R.color.green))
                        .padding(vertical = 16.dp)
                        .clickable {
                            viewModel.viewModelScope.launch {
                                withContext(Dispatchers.Main) {
                                    navController.navigate(Screen.AddPlantScreen.route)
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.krestik),
                            contentDescription = "Крест",
                            alignment = Alignment.Center
                        )
                        Text(
                            text = "Новое растение",
                            style = TextStyle(
                                fontSize = 24.sp,
                                color = colorResource(R.color.beige),
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlantItem(
    name: String,
    navController: NavController,
    plant: Plant
) {
    val widthAndHeight = LocalConfiguration.current.screenWidthDp.dp * 0.25f
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.beige)
        ),
        onClick = { navController.navigate("infoPlant/${plant.id}") }
    ) {
        Column(
            Modifier
                .padding(end = 16.dp)
        ) {
            Box() {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ellipse_1),
                    contentDescription = "ellipse",
                    Modifier
                        .width(widthAndHeight)
                        .height(widthAndHeight)
                )
                plant.photoUri?.let { ImageVector.vectorResource(it) }?.let {
                    Image(
                        imageVector = it,
                        contentDescription = "ellipse",
                        Modifier
                            .width(widthAndHeight)
                            .height(widthAndHeight)
                    )
                }
            }
            Text(
                text = name,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(
                            R.font.inter_medium
                        )
                    ),
                    fontSize = 32.sp,
                    color = colorResource(R.color.darkBrown)

                )
            )
        }
    }
}


@Composable
fun PlantsToWaterScreen(viewModel: PlantViewModel, navController: NavController) {
    val plantsToWater by viewModel.plantsToWater.collectAsState(initial = emptyList())

    if (plantsToWater.isEmpty()) {
        Text(
            text = "Все растения политы!",
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.inter_medium)
                ),
                color = colorResource(R.color.darkBrown),
                fontSize = 32.sp
            )

        )
    } else {
        LazyRow(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(plantsToWater) { plant ->
                plant.name?.let { PlantItem(it, navController, plant) }
            }
        }
    }
}

