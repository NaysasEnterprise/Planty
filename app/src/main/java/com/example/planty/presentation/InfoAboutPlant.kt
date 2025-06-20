package com.example.planty.presentation

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.planty.R
import com.example.planty.entity.Plant
import com.example.planty.garbage.Converters
import com.example.planty.garbage.Screen
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun InfoAboutPlantScreen(
    viewModel: PlantViewModel = hiltViewModel(),
    plantId: Int,
    navHostController: NavHostController
) {
    val rememberWidth = LocalConfiguration.current.screenWidthDp.dp // TODO: viewModel
    val rememberHeight = LocalConfiguration.current.screenHeightDp.dp
    val plant by viewModel.getPlantById(plantId).collectAsState(initial = null)
    val context = LocalContext.current
    PlantyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.beige))
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Последний полив: " + plant?.lastWateringDate.toString(),
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(
                            R.font.inter_medium
                        )
                    ),
                    color = colorResource(R.color.darkBrown),
                    fontSize = 24.sp
                )
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(rememberHeight * 0.35f)

            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ellipse_1),
                    contentDescription = "ellipse",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(rememberHeight * 0.3f)
                )
                plant?.photoUri?.let { ImageVector.vectorResource(it) }?.let {
                    Image(
                        imageVector = it,
                        contentDescription = stringResource(R.string.add_plant),
                        modifier = Modifier
                            .padding()
                            .fillMaxWidth()
                            .height(rememberHeight * 0.25f)
                    )
                }
            }
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    onClick = {
                        plant?.let { viewModel.deletePlant(it) }
                        navHostController.navigate(Screen.MainScreen.route)
                        Toast.makeText(context,"Растение удалено!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .width(rememberWidth * 0.45f)
                        .height(rememberHeight * 0.055f),
                    colors = CardDefaults.cardColors().copy(
                        containerColor = colorResource(R.color.red)
                    )
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            text = stringResource(R.string.delete_button),
                            style = TextStyle(
                                fontFamily = FontFamily(
                                    Font(
                                        R.font.inter_semibold
                                    )
                                ),
                                color = colorResource(R.color.beige),
                                fontSize = 24.sp
                            )
                        )
                    }
                }
            }
            Box(
                Modifier
                    .width(rememberWidth * 0.65f)
                    .height(rememberHeight * 0.08f)
                    .clip(RoundedCornerShape(30))
                    .background(colorResource(R.color.green)),
                contentAlignment = Alignment.Center,
            ) {
                plant?.name?.let {
                    Text(
                        text = it,
                        modifier = Modifier,
                        style = TextStyle(
                            fontFamily = FontFamily(
                                Font(
                                    R.font.inter_semibold
                                )
                            ),
                            color = colorResource(R.color.beige),
                            fontSize = 32.sp
                        )
                    )
                }
            }
            Column(
                Modifier.fillMaxWidth()
            ) {
                plant?.let { InformationPlant(it) }
            }
        }
    }
}


@Composable
fun InformationPlant(plant: Plant) {
    Column() {
        plant.species?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(
                            R.font.inter_medium
                        )
                    ),
                    color = colorResource(R.color.darkBrown),
                    fontSize = 32.sp
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        Text(
            text = plant.getCreationDateFormatted(),
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_medium
                    )
                ),
                color = colorResource(R.color.darkBrown),
                fontSize = 32.sp
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(
            text = "Частота полива(лето/зима): раз в " + plant.getWateringIntervalFormatted(),
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_medium
                    )
                ),
                color = colorResource(R.color.darkBrown),
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        plant.careFeatures?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(
                            R.font.inter_medium
                        )
                    ),
                    color = colorResource(R.color.darkBrown),
                    fontSize = 24.sp
                )
            )
        }
    }
}
