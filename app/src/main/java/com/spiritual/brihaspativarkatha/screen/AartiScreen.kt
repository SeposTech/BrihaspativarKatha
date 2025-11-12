package com.spiritual.brihaspativarkatha.screen

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AartiScreen(
    title: String,
    aartiText: String,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFDAA520) // Saffron tone
                )
            )
        },
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(aartiText))
                        Toast.makeText(context, "आरती कॉपी हो गई ✅", Toast.LENGTH_SHORT).show()
                    },
                    containerColor = Color(0xFFFFECB3)
                ) {
                    Icon(
                        Icons.Default.ContentCopy,
                        contentDescription = "Copy",
                        tint = Color(0xFF4A2800)
                    )
                }

                FloatingActionButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_SUBJECT, title)
                            putExtra(Intent.EXTRA_TEXT, aartiText)
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "आरती शेयर करें"))
                    },
                    containerColor = Color(0xFFFFF3C2)
                ) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = "Share",
                        tint = Color(0xFF4A2800)
                    )
                }
            }
        },
        bottomBar = {
            // Fixed footer bar (अब किसी भी device पर cut नहीं होगा)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFDAA520),
                                Color(0xFFF7D36F)
                            )
                        )
                    )
                    .padding(vertical = 12.dp)
                    .navigationBarsPadding(), // ✅ Safe area fix
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🔱 बोलो बृहस्पतिदेव भगवान की जय 🙏",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFFFFF8E1), // Light yellow
                            Color(0xFFFFECB3), // Warm cream
                            Color(0xFFFFF3C2)  // Soft golden
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            SelectionContainer {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = aartiText,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        lineHeight = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF4A2800),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}
