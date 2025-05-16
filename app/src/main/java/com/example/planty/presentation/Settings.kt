package com.example.planty.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.planty.R
import com.example.planty.garbage.Gender
import com.example.planty.ui.theme.PlantyTheme

@Composable
fun SettingsScreen(
    mainViewModel: PlantViewModel = hiltViewModel()
) {
    val rememberWidth = LocalConfiguration.current.screenWidthDp.dp // TODO: viewModel
    val rememberHeight = LocalConfiguration.current.screenHeightDp.dp
    val context = LocalContext.current
    val selectedGender = remember { mutableStateOf<Gender?>(null) }
    PlantyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.beige))
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.settings),
                color = colorResource(R.color.darkBrown),
                style = TextStyle(
                    fontSize = 60.sp,

                    ),
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_bold
                    )
                ),
            )

            Text(
                text = stringResource(R.string.profile),
                color = colorResource(R.color.darkBrown),
                style = TextStyle(
                    fontSize = 40.sp,
                ),
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_medium
                    )
                )

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.clickable {
                        selectedGender.value = Gender.FEMALE
                    },
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedGender.value == Gender.FEMALE) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ellipseperson),
                            contentDescription = stringResource(R.string.ellipseProfile)
                        )
                    }
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.woman),
                        contentDescription = stringResource(R.string.woman)
                    )
                }

                Box(
                    modifier = Modifier.clickable {
                        selectedGender.value = Gender.MALE
                    },
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedGender.value == Gender.MALE) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ellipseperson),
                            contentDescription = stringResource(R.string.ellipseProfile)
                        )
                    }
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.man),
                        contentDescription = stringResource(R.string.man)
                    )
                }
            }
            var message = remember { mutableStateOf("") } // TODO: viewModel
            val maxChar = 20
            Box(
                modifier = Modifier
                    .width(rememberWidth * 0.6f)
                    .height(rememberHeight * 0.12f)
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
                            text = stringResource(R.string.name),
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
            Text(
                text = stringResource(R.string.notifications),
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(
                            R.font.inter_medium
                        )
                    ),
                    fontSize = 40.sp,
                    color = colorResource(R.color.darkBrown)
                )
            )
            Box(// TODO: стилизовать
                modifier = Modifier.size(height = 100.dp, width = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Switch(
                    checked = false,
                    onCheckedChange = {

                    }, // TODO: viewModel
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = colorResource(R.color.beige),
                        checkedThumbColor = colorResource(R.color.beige),
                        disabledCheckedThumbColor = colorResource(R.color.beige),
                        disabledUncheckedThumbColor = colorResource(R.color.beige),
                        uncheckedBorderColor = colorResource(R.color.beige),
                        checkedBorderColor = colorResource(R.color.beige),
                        disabledCheckedBorderColor = colorResource(R.color.beige),
                        disabledUncheckedBorderColor = colorResource(R.color.beige),
                        uncheckedTrackColor = colorResource(R.color.lightBrown),
                        checkedTrackColor = colorResource(R.color.lightBrown),
                        disabledCheckedTrackColor = colorResource(R.color.lightBrown),
                        disabledUncheckedTrackColor = colorResource(R.color.lightBrown)
                    ),

                    )
            }
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) { // TODO: стилизовать
                Button(
                    onClick = {},
                    content = { Text(stringResource(R.string.saveButton)) }// TODO: viewModel
                )
            }
        }


    }
}


@Composable
@Preview(showBackground = true)
fun PreviewSettingsScreen() {
    SettingsScreen()
}