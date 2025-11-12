package com.spiritual.brihaspativarkatha.screen

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // 🔹 Animation state
    var scale by remember { mutableStateOf(0f) }
    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
    )

    var alpha by remember { mutableStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 2000)
    )

    // 🔹 Start animation when screen loads
    LaunchedEffect(Unit) {
        scale = 1f
        alpha = 1f
        delay(3000) // 3 second splash
        navController.navigate("Home") {
            popUpTo("Splash") { inclusive = true }
        }
    }

    // 🔹 Background gradient (spiritual gold aura)
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 🕉️ Om symbol with animation
            Text(
                text = "🕉️",
                fontSize = (100 * animatedScale).sp,
                color = Color(0xFFB8860B),
                textAlign = TextAlign.Center,
                modifier = Modifier.scale(animatedScale)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // App name
            Text(
                text = "बृहस्पतिवार व्रत कथा",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A2800),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .alpha(animatedAlpha)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "भगवान बृहस्पति की कृपा आप पर बनी रहे 🌼",
                fontSize = 16.sp,
                color = Color(0xFF6D4C41),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .alpha(animatedAlpha)
            )
        }
    }
}
