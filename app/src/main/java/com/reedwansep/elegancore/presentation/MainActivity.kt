package com.reedwansep.elegancore.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text
import com.google.firebase.analytics.FirebaseAnalytics
import com.reedwansep.elegancore.presentation.theme.EleganCoreTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle().apply {
            putString("screen_name", "MainActivity")
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    EleganCoreTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CasioWatchFace()
        }
    }
}

@Composable
fun CasioWatchFace() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DigitalTime()
        DigitalDate()
        BatteryIndicator()
    }
}

@Composable
fun DigitalTime() {
    val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
    Text(
        text = currentTime,
        color = Color.Cyan,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun DigitalDate() {
    val currentDate = SimpleDateFormat("EEE, MMM dd yyyy", Locale.getDefault()).format(Date())
    Text(
        text = currentDate,
        color = Color.LightGray,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    )
}

@Composable
fun BatteryIndicator() {
    val batteryLevel = 85 // Dummy battery level
    Text(
        text = "Battery: $batteryLevel%",
        color = Color.Green,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp()
}
