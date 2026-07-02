package com.spiritual.brihaspativarkatha.screen

import android.app.Activity
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsEvents
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsHelper
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

@Composable
fun SplashScreen(navController: NavController, appUpdateManager: AppUpdateManager) {
    TrackScreen("SplashScreen")
    LaunchedEffect(Unit) {
        AnalyticsHelper.logEvent(
            AnalyticsEvents.SPLASH_SCREEN_OPEN,
            mapOf("screen" to "splash_screen")
        )
    }
    val context = LocalContext.current
    val activity = context as? Activity

    var scale by remember { mutableStateOf(0f) }
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(1500, easing = FastOutSlowInEasing)
    )

    var alpha by remember { mutableStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(2000)
    )

    LaunchedEffect(Unit) {

        scale = 1f
        alpha = 1f

        try {

            val appUpdateInfo = appUpdateManager.appUpdateInfo.await()

            if (appUpdateInfo.updateAvailability() ==
                UpdateAvailability.UPDATE_AVAILABLE &&
                appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {

                activity?.let {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        it,
                        100
                    )
                }

            } else {

                delay(3000)

                navController.navigate("Home") {
                    popUpTo("Splash") { inclusive = true }
                }
            }

        } catch (e: Exception) {

            delay(3000)

            navController.navigate("Home") {
                popUpTo("Splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFFF8E1),
                        Color(0xFFFFD54F),
                        Color(0xFFF9A825)
                    ),
                    radius = 800f
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "🕉️",
                fontSize = (100 * animatedScale).sp,
                color = Color(0xFFB8860B),
                modifier = Modifier.scale(animatedScale)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "बृहस्पतिवार व्रत कथा",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A2800),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(animatedAlpha)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "भगवान बृहस्पति की कृपा आप पर बनी रहे 🌼",
                fontSize = 16.sp,
                color = Color(0xFF6D4C41),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(animatedAlpha)
            )
        }
    }


}

@Composable
fun TrackScreen(screenName: String) {
    LaunchedEffect(Unit) {
        AnalyticsHelper.trackScreen(screenName)
    }
}