package com.spiritual.brihaspativarkatha.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.spiritual.brihaspativarkatha.ads.BannerAdView
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AartiDetailScreen(navController: NavController, resId: Int, onBack: () -> Unit = {}) {
    TrackScreenAartiDetails("AartiDetailScreen")
    val context = LocalContext.current

    val aartiText = remember {
        readRawText(context, resId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "🪔 दैनिक आरती",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFDAA520)),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding() // 🔥 Fix for home button overlap
            ) {
                BannerAdView(
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { padding ->

        // 🌈 Full Background Gradient
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFF3E0),
                            Color(0xFFFFE0B2),
                            Color(0xFFFFCC80)
                        )
                    )
                )
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = aartiText,
                fontSize = 20.sp,
                color = Color(0xFF4E342E),
                lineHeight = 32.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

fun readRawText(context: Context, resId: Int): String {
    return context.resources.openRawResource(resId)
        .bufferedReader()
        .use { it.readText() }
}

@Composable
fun TrackScreenAartiDetails(screenName: String) {
    LaunchedEffect(Unit) {
        AnalyticsHelper.trackScreen(screenName)
    }
}