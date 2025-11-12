package com.spiritual.brihaspativarkatha.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KathaVidhiScreen(onBack: () -> Unit = {}) {
    var visible by remember { mutableStateOf(false) }

    // Animate content on launch
    LaunchedEffect(Unit) { visible = true }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "बृहस्पतिवार व्रत की विधि",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
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
                        colors = listOf(
                            Color(0xFFFFF8E1),
                            Color(0xFFFFECB3),
                            Color(0xFFFFF3C2)
                        )
                    )
                )
                .padding(paddingValues)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Heading Card
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .shadow(4.dp, RoundedCornerShape(12.dp))
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(Color(0xFFFFD54F), Color(0xFFFFC107))
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 12.dp)
                    ) {
                        Text(
                            text = "🌼 व्रत विधि 🌼",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4A2800),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    // Content Box
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 2.dp,
                                brush = Brush.linearGradient(
                                    listOf(Color(0xFFFFC107), Color(0xFFFFD54F))
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(Color(0xFFFFFBEB), shape = RoundedCornerShape(12.dp))
                            .padding(16.dp)
                    ) {
                        val bulletPoints = listOf(
                            "बृहस्पतिवार के दिन प्रातःकाल स्नान कर स्वच्छ पीले वस्त्र धारण करें।",
                            "घर में या मंदिर में भगवान बृहस्पति (गुरु) की तस्वीर या मूर्ति स्थापित करें।",
                            "पूजन स्थल को पीले कपड़े से सजाएँ और पीले फूल, चने की दाल, हल्दी, गुड़ और केले का प्रसाद रखें।",
                            "भगवान बृहस्पति की आरती करें और 'ॐ बृं बृहस्पतये नमः' मंत्र का जाप कम से कम 108 बार करें।",
                            "इस दिन पीले खाद्य पदार्थ जैसे चने की दाल, बेसन, हल्दी वाले भोजन का सेवन करें।",
                            "दिन में एक समय भोजन करें और शाम को कथा व आरती करें।",
                            "व्रत के दौरान किसी का अपमान न करें और असत्य न बोलें।",
                            "लगातार 16 बृहस्पतिवार का व्रत रखने से सभी मनोकामनाएं पूर्ण होती हैं।"
                        )

                        bulletPoints.forEachIndexed { index, point ->
                            Text(
                                text = "👉 ${point}",
                                fontSize = 18.sp,
                                lineHeight = 28.sp,
                                color = Color(0xFF4A2800),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "🙏 व्रत का पालन श्रद्धा और विश्वास के साथ करें। 🙏",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF6D4C41),
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.None
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KathaVidhiScreenPreview() {
    MaterialTheme {
        KathaVidhiScreen()
    }
}
