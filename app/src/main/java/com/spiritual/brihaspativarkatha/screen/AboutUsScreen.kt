package com.spiritual.brihaspativarkatha.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spiritual.brihaspativarkatha.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(onBack: () -> Unit = {}) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "हमारे बारे में",
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
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFFFF8E1),
                            Color(0xFFFFECB3),
                            Color(0xFFFFF3C2)
                        )
                    )
                )
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // App Logo
            Image(
                painter = painterResource(id = R.drawable.ic_app_logo_1024), // your app logo
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "🌼 ब्रहस्पतिवार व्रत कथा एप 🌼",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6D4C41)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "यह ऐप भक्तों को भगवान बृहस्पति देव की कृपा प्राप्त करने हेतु व्रत कथा, पूजन विधि और आध्यात्मिक ज्ञान प्रदान करने के उद्देश्य से बनाया गया है। इस ऐप के माध्यम से आप सरल भाषा में कथा पढ़ सकते हैं, व्रत का महत्व जान सकते हैं और अपनी श्रद्धा को और दृढ़ कर सकते हैं।",
                fontSize = 16.sp,
                lineHeight = 26.sp,
                color = Color(0xFF4A2800),
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "🌿 हमारा उद्देश्य 🌿",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF795548)
            )

            Text(
                text = "भारतीय संस्कृति और देवी-देवताओं के प्रति लोगों में श्रद्धा, आस्था और जागरूकता बढ़ाना — ताकि आधुनिक तकनीक के माध्यम से भक्ति हर घर तक पहुँच सके।",
                fontSize = 16.sp,
                color = Color(0xFF4A2800),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Divider(
                color = Color(0xFFD7CCC8),
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "📩 हमसे संपर्क करें",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF795548)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.Email, contentDescription = null, tint = Color(0xFF6D4C41))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "himanshumehra99@gmail.com",
                    fontSize = 15.sp,
                    color = Color(0xFF4A2800)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "🙏 धन्यवाद! आपके जीवन में सदैव गुरु कृपा बनी रहे 🙏",
                fontSize = 15.sp,
                color = Color(0xFF4A2800),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
