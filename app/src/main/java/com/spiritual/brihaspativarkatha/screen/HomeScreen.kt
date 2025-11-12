package com.spiritual.brihaspativarkatha.screen

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onTopicClick: (String) -> Unit = {}, navController: NavController) {
    val topics = listOf(
        "🌼 बृहस्पतिवार व्रत का महत्व",
        "🙏 व्रत विधि",
        "📖 व्रत कथा",
        "🪔 बृहस्पति देव की आरती",
        "🌸 ओम जय जगदीश हरे आरती",
        "💰 लक्ष्मी जी की आरती"
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "बृहस्पतिवार व्रत कथा",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 22.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFDAA520)
                )
            )
        },
        bottomBar = {
            FooterBar(navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFFFFF8E1),
                            Color(0xFFFFECB3),
                            Color(0xFFFFF3C2)
                        )
                    )
                )
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🙏 हर गुरुवार को भगवान बृहस्पति की कृपा पाने के लिए व्रत, पूजा और कथा सुनें 🙏",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF6D4C41),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(topics) { topic ->
                        TopicCard(title = topic, onClick = { onTopicClick(topic) })
                    }
                }
            }
        }
    }
}

@Composable
fun TopicCard(title: String, onClick: () -> Unit) {
    var isPressed by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isPressed) Color(0xFFFFF3C2) else Color.White
    )
    val scale by animateFloatAsState(targetValue = if (isPressed) 0.97f else 1f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scale)
            .clickable {
                isPressed = true
                onClick()
                isPressed = false
            },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4A2800)
            )
            Image(
                painter = painterResource(android.R.drawable.ic_menu_info_details),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun FooterBar(navController: NavController) {
    val context = LocalContext.current

    NavigationBar(
        containerColor = Color(0xFFFFF3C2)
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { openPlayStoreForRating(context) },
            icon = { Icon(Icons.Default.StarRate, contentDescription = "Rate") },
            label = { Text("Rate Us") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4A2800),
                selectedTextColor = Color(0xFF4A2800)
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { shareApp(context) },
            icon = { Icon(Icons.Default.Share, contentDescription = "Share") },
            label = { Text("Share") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4A2800),
                selectedTextColor = Color(0xFF4A2800)
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("About") },
            icon = { Icon(Icons.Default.Info, contentDescription = "About") },
            label = { Text("About") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4A2800),
                selectedTextColor = Color(0xFF4A2800)
            )
        )
    }
}

// 🔹 Function to open Play Store for Rating
fun openPlayStoreForRating(context: Context) {
    val appPackageName = context.packageName
    try {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appPackageName")
            )
        )
    } catch (e: ActivityNotFoundException) {
        context.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            )
        )
    }
}

// 🔹 Function to Share App
fun shareApp(context: Context) {
    val appPackageName = context.packageName
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(
            Intent.EXTRA_TEXT,
            "🙏 बृहस्पतिवार व्रत कथा ऐप डाउनलोड करें:\nhttps://play.google.com/store/apps/details?id=$appPackageName"
        )
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share App"))
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(navController = rememberNavController())
    }
}
