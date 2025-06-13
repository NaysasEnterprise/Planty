package com.example.planty.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planty.R
import com.example.planty.data.notifications.NotificationServiceLocal
import com.example.planty.ui.theme.PlantyTheme
import com.google.firebase.messaging.FirebaseMessaging

@Composable
fun NotificationsScreen() {
    val rememberWidth = LocalConfiguration.current.screenWidthDp.dp // TODO: viewModel
    val rememberHeight = LocalConfiguration.current.screenHeightDp.dp
    val context = LocalContext.current
    PlantyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.beige))
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = stringResource(R.string.notifications),
                color = colorResource(R.color.darkBrown),
                style = TextStyle(
                    fontSize = 48.sp,

                    ),
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_bold
                    )
                ),
            )
            Text(
                modifier = Modifier
                    .padding(top = 32.dp),
                text = stringResource(R.string.needsWater),
                color = colorResource(R.color.darkBrown),
                style = TextStyle(
                    fontSize = 40.sp,

                    ),
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_medium
                    )
                ),
            )

            // TODO: добавить лист, в который будут попадать уведомления 

        }
    }
}
