package com.example.planty.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.planty.R
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun LoadScreen() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    PlantyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.beige))
                .padding(top = 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.planty_circle),
                    contentDescription = "Planty Icon",
                    modifier = Modifier
                        .size(screenWidth * 0.78f),
                    tint = Color.Unspecified,
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Planty",
                        color = colorResource(R.color.beige),
                        style = TextStyle(
                            fontSize = 64.sp,
                            shadow = Shadow(
                                blurRadius = 4f,
                                offset = Offset(y = 4f, x = 0f),
                                color = Color.Black.copy(alpha = 0.25f)
                            )
                        ),
                        fontFamily = FontFamily(
                            Font(
                                R.font.inter_bold
                            )
                        )
                    )
                    Text(
                        text = "Happy plants, happy life",
                        color = colorResource(R.color.beige),
                        style = TextStyle(
                            fontSize = 24.sp,
                            shadow = Shadow(
                                blurRadius = 4f,
                                offset = Offset(y = 4f, x = 0f),
                                color = Color.Black.copy(alpha = 0.25f)
                            )
                        ),
                        fontFamily = FontFamily(
                            Font(
                                R.font.inter_medium
                            )
                        ),
                    )
                }
            }
            Box() {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.bottom_land),
                    contentDescription = "Planty Icon",
                    modifier = Modifier
                )
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.flower),
                    contentDescription = "Flower",
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewLoadScreen() {
    LoadScreen()
}