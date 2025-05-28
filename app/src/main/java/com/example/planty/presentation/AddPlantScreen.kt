package com.example.planty.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.planty.R
import com.example.planty.data.model.DefaultPlants
import com.example.planty.entity.Plant
import com.example.planty.ui.theme.PlantyTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlantScreen(
    viewModel: PlantViewModel = hiltViewModel()
) {
    val rememberWidth = LocalConfiguration.current.screenWidthDp.dp // TODO: viewModel
    val rememberHeight = LocalConfiguration.current.screenHeightDp.dp
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedPlant by remember {
        mutableStateOf(DefaultPlants.plantsList[0])
    }
    var searchText by remember {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()
    val filteredOptions = remember(searchText, DefaultPlants.plantsList) {
        if (searchText.isBlank()) {
            DefaultPlants.plantsList
        } else {
            val prefixMatches = DefaultPlants.plantsList.filter { plant ->
                plant.name?.startsWith(searchText, ignoreCase = true) ?: false
            }
            prefixMatches.ifEmpty {
                DefaultPlants.plantsList.filter {
                    it.name?.contains(searchText, ignoreCase = true) ?: false
                }
            }
        }
    }
    PlantyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.beige))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(rememberHeight * 0.35f)

            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.addplant),
                    contentDescription = stringResource(R.string.add_plant),
                    modifier = Modifier
                        .padding()
                )
            }
            var message = remember { mutableStateOf("") } // TODO: viewModel
            val maxChar = 20
            Box(
                modifier = Modifier
                    .width(rememberWidth * 0.68f)
                    .height(rememberHeight * 0.12f),
                contentAlignment = Alignment.Center
            ) {

                TextField(
                    value = message.value,
                    textStyle = TextStyle(
                        fontFamily = FontFamily(
                            Font(
                                R.font.inter_bold
                            )
                        ),
                        fontSize = 32.sp,
                        color = colorResource(R.color.beige)
                    ),
                    onValueChange = {
                        if (it.length <= maxChar) message.value = it
                        else Toast.makeText(
                            context,
                            context.getString(R.string.toastFieldText),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.name_plant),
                            style = TextStyle(
                                fontFamily = FontFamily(
                                    Font(
                                        R.font.inter_bold
                                    )
                                ),
                                fontSize = 32.sp,
                                color = colorResource(R.color.beige)
                            )
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .clip(RoundedCornerShape(40)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorResource(R.color.green),
                        unfocusedContainerColor = colorResource(R.color.green),
                        disabledContainerColor = colorResource(R.color.green),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),

                    )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it },

                    ) {
                    TextField(
                        modifier = Modifier.menuAnchor(),
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            if (it.isNotEmpty()) expanded = true
                        },
                        textStyle = TextStyle(
                            fontFamily = FontFamily(
                                Font(
                                    R.font.inter_bold
                                )
                            ),
                            fontSize = 32.sp,
                            color = colorResource(R.color.beige)
                        ),
                        placeholder = {
                            Text(
                                text = "Тип растения",
                                style = TextStyle(
                                    fontFamily = FontFamily(
                                        Font(
                                            R.font.inter_bold
                                        )
                                    ),
                                    fontSize = 32.sp,
                                    color = colorResource(R.color.beige)
                                )
                            )
                        },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    )
                    ExposedDropdownMenu(
                        expanded = expanded && filteredOptions.isNotEmpty(),
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .scrollable(scrollState, orientation = Orientation.Vertical)
                            .height(118.dp),
                    ) {
                        filteredOptions.forEach { plant ->
                            DropdownMenuItem(
                                text = { plant.name?.let { Text(it) } },
                                onClick = {
                                    selectedPlant = plant
                                    searchText = plant.name.toString()
                                    expanded = false
                                },
                            )
                        }
                    }
                }
            }
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    onClick = {
                        viewModel.viewModelScope.launch {
                            viewModel.addPlant(Plant(name = searchText))
                            Toast
                                .makeText(context, "Растение добавлено", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                    modifier = Modifier
                        .width(rememberWidth * 0.75f)
                        .height(rememberHeight * 0.08f),
                    colors = CardDefaults.cardColors().copy(
                        containerColor = colorResource(R.color.green)
                    )
                ) {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.saveButton),
                            style = TextStyle(
                                fontFamily = FontFamily(
                                    Font(
                                        R.font.inter_bold
                                    )
                                ),
                                color = colorResource(R.color.beige),
                                fontSize = 36.sp
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewAddPlantScreen() {
    AddPlantScreen()
}