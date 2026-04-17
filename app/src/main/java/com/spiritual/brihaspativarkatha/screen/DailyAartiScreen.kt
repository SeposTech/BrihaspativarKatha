package com.spiritual.brihaspativarkatha.screen

import android.R
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.spiritual.brihaspativarkatha.viewmodel.AartiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyAartiScreen(navController: NavController,onItemClick: (Int) -> Unit,viewModel: AartiViewModel = viewModel(),onBack: () -> Unit = {}) {

    val aartiList by viewModel.aartiList.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "दैनिक पूजा / आरती",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFDAA520)
                ),
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

            LazyColumn(
                contentPadding = PaddingValues(
                    top = 12.dp,
                    bottom = 60.dp // 👈 last item cut fix + banner ke liye space
                ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                items(aartiList) { day ->
                    AartiCardTopic(day.title) {
                        //onItemClick(day.resId)
                        navController.navigate("aartiDetail/${day.resId}")
                    }
                }
            }
        }
    }
}

@Composable
fun AartiCardTopic(title: String, onClick: () -> Unit) {

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
                painter = painterResource(R.drawable.ic_menu_info_details),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }

}