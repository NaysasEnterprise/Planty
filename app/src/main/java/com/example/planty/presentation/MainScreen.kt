package com.example.planty.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.planty.R
import com.example.planty.ui.theme.PlantyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun MainScreen(
    viewModel: PlantViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val plants by viewModel.plants.collectAsState(initial = emptyList())
    PlantyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.beige))
                .padding(16.dp)
        ) {
            Text(
                text = "Мои растения",
                style = TextStyle(
                    fontSize = 32.sp,
                    color = colorResource(R.color.darkBrown),
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )
            LazyRow (
            ) {
                items(plants) { plant ->
                    PlantItem(plant.name)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .clickable {
                        viewModel.viewModelScope.launch {
                            withContext(Dispatchers.IO) {
                                viewModel.addPlant()
                            }
                        }
                        Toast.makeText(context,"Растение добавлено",Toast.LENGTH_SHORT).show()
                    }
            ) {
                Text(
                    text = "+ Новое растение",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = colorResource(R.color.green),
                    )
                )
            }
        }
    }
}

@Composable
fun PlantItem(name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.beige)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row{
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = colorResource(R.color.darkBrown),
                        fontWeight = FontWeight.Medium
                    )
                )


            }

        }
    }
}
