package com.spiritual.brihaspativarkatha.screen

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class FestivalItem(
    val name: String,
    val date: String
)

private val hindiFestivals = listOf(
    FestivalItem("मकर संक्रांति", "14 जनवरी"),
    FestivalItem("वसंत पंचमी", "23 जनवरी"),
    FestivalItem("महाशिवरात्रि", "15 फरवरी"),
    FestivalItem("होली", "04 मार्च"),
    FestivalItem("राम नवमी", "27 मार्च"),
    FestivalItem("हनुमान जयंती", "02 अप्रैल"),
    FestivalItem("अक्षय तृतीया", "20 अप्रैल"),
    FestivalItem("गंगा दशहरा", "25 मई"),
    FestivalItem("जगन्नाथ रथ यात्रा", "16 जुलाई"),
    FestivalItem("गुरु पूर्णिमा", "29 जुलाई"),
    FestivalItem("रक्षाबंधन", "28 अगस्त"),
    FestivalItem("कृष्ण जन्माष्टमी", "04 सितंबर"),
    FestivalItem("गणेश चतुर्थी", "14 सितंबर"),
    FestivalItem("नवरात्रि प्रारंभ", "11 अक्टूबर"),
    FestivalItem("दशहरा", "20 अक्टूबर"),
    FestivalItem("करवा चौथ", "29 अक्टूबर"),
    FestivalItem("धनतेरस", "06 नवंबर"),
    FestivalItem("दीपावली", "08 नवंबर"),
    FestivalItem("भैया दूज", "11 नवंबर"),
    FestivalItem("छठ पूजा", "15 नवंबर"),
    FestivalItem("देवउठनी एकादशी", "20 नवंबर"),
    FestivalItem("गुरु नानक जयंती", "24 नवंबर"),
    FestivalItem("मार्गशीर्ष गुरुवार प्रारंभ", "दिसंबर (तिथि अनुसार)")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanchangScreen(
    onBackPress: () -> Unit
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFF3E0),
            Color(0xFFFFE0B2),
            Color(0xFFFFCC80)
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "पंचांग",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFFFFB74D),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = Color(0xFFE65100),
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "हिंदी त्योहार एवं तिथियाँ",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFBF360C)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }

                items(hindiFestivals) { festival ->
                    FestivalCard(festival = festival)
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "नोट: तिथियाँ पंचांग और स्थान के अनुसार बदल सकती हैं।",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF5D4037)
                    )
                }
            }
        }
    }
}

@Composable
private fun FestivalCard(festival: FestivalItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, Color(0xFFFFCC80)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.96f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Celebration,
                    contentDescription = null,
                    tint = Color(0xFFE65100),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = festival.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4E342E)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            AssistChip(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(
                        text = festival.date,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF5D4037)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color(0xFFFFF3E0),
                    labelColor = Color(0xFF5D4037),
                    leadingIconContentColor = Color(0xFFEF6C00)
                ),
                border = AssistChipDefaults.assistChipBorder(
                    enabled = true,
                    borderColor = Color(0xFFFFB74D)
                )
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun PanchangScreenDarkPreview() {
    MaterialTheme {
        PanchangScreen(onBackPress = {})
    }
}