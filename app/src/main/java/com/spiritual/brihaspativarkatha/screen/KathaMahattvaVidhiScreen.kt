package com.spiritual.brihaspativarkatha.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KathaMahattvaVidhiScreen(onBack: () -> Unit = {}) {
    TrackScreenKathMahattva("KathaMahattvaVidhiScreen")
    val kathaText = """
        भगवान बृहस्पतिदेव की पूजा-अर्चना के लिए बृहस्पतिवार को व्रत रखकर, बृहस्पतिवार की व्रत कथा को पढ़ने अथवा किसी दूसरे स्त्री-पुरुष द्वारा सुनने की प्राचीन परम्परा रही है। 
        बृहस्पतिवार का व्रत रखने और व्रत-कथा सुनने से स्त्री-पुरुषों की सभी मनोकामनाएं पूरी होती हैं। 
        इस व्रत से धन-सम्पत्ति की प्राप्ति होती है। निःसंतानों को पुत्र-प्राप्ति होती है। परिवार में सुख-शांति बनी रहती है तथा सभी आनन्दपूर्वक रहते हैं।

        बृहस्पतिवार को सूर्योदय से पहले उठकर स्नानादि से निवृत्त होकर, भगवान बृहस्पतिदेव का स्मरण करते हुए व्रत रखना चाहिए। व्रत रखने वाले को घर के किसी कक्ष में छोटा अथवा बड़ा पूजास्थल बनाकर उसमें भगवान बृहस्पतिदेव की
        प्रतिमा की स्थापना करनी चाहिए। घर के समीप किसी मंदिर में जाकर भी भगवान बृहस्पतिदेव की पूजा की जा सकती है। भगवान बृहस्पतिदेव पीले रंग के पुष्प और पूजा की पीले रंग की सामग्री को विशेष रूप से पसंद करते हैं। 
        इसलिए स्नान के बाद पीले रंग के वस्त्र धारण करने का विशेष महत्त्व बताया गया है।
    """.trimIndent()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "बृहस्पतिवार व्रत का महत्त्व व विधि",
                        fontWeight = FontWeight.Bold,
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
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF8E1))
                    .padding(12.dp)
                    .navigationBarsPadding()
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "🌸 बोलो बृहस्पति देव की जय 🙏",
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF6D4C41),
                    fontSize = 16.sp
                )
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
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
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                AnimatedVisibility(visible = true, enter = fadeIn()) {
                    Text(
                        text = kathaText,
                        fontSize = 18.sp,
                        lineHeight = 28.sp,
                        color = Color(0xFF4A2800),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@Composable
fun TrackScreenKathMahattva(screenName: String) {
    LaunchedEffect(Unit) {
        AnalyticsHelper.trackScreen(screenName)
    }
}
