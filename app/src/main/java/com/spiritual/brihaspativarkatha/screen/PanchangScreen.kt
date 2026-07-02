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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Today
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsEvents
import com.spiritual.brihaspativarkatha.data.analytics.AnalyticsHelper
import java.util.Calendar
import java.util.concurrent.TimeUnit

private val hindiFestivals = listOf(
    FestivalItem("मकर संक्रांति", 14, 1, "14 जनवरी"),
    FestivalItem("वसंत पंचमी", 23, 1, "23 जनवरी"),
    FestivalItem("महाशिवरात्रि", 15, 2, "15 फरवरी"),
    FestivalItem("होली", 4, 3, "04 मार्च"),
    FestivalItem("राम नवमी", 27, 3, "27 मार्च"),
    FestivalItem("हनुमान जयंती", 2, 4, "02 अप्रैल"),
    FestivalItem("अक्षय तृतीया", 20, 4, "20 अप्रैल"),
    FestivalItem("गंगा दशहरा", 25, 5, "25 मई"),
    FestivalItem("जगन्नाथ रथ यात्रा", 16, 7, "16 जुलाई"),
    FestivalItem("गुरु पूर्णिमा", 29, 7, "29 जुलाई"),
    FestivalItem("रक्षाबंधन", 28, 8, "28 अगस्त"),
    FestivalItem("कृष्ण जन्माष्टमी", 4, 9, "04 सितंबर"),
    FestivalItem("गणेश चतुर्थी", 14, 9, "14 सितंबर"),
    FestivalItem("नवरात्रि प्रारंभ", 11, 10, "11 अक्टूबर"),
    FestivalItem("दशहरा", 20, 10, "20 अक्टूबर"),
    FestivalItem("करवा चौथ", 29, 10, "29 अक्टूबर"),
    FestivalItem("धनतेरस", 6, 11, "06 नवंबर"),
    FestivalItem("दीपावली", 8, 11, "08 नवंबर"),
    FestivalItem("भैया दूज", 11, 11, "11 नवंबर"),
    FestivalItem("छठ पूजा", 15, 11, "15 नवंबर"),
    FestivalItem("देवउठनी एकादशी", 20, 11, "20 नवंबर"),
    FestivalItem("गुरु नानक जयंती", 24, 11, "24 नवंबर"),
    FestivalItem("मार्गशीर्ष गुरुवार प्रारंभ", 5, 12, "दिसंबर (तिथि अनुसार)")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanchangScreen(
    onBackPress: () -> Unit
) {
    LaunchedEffect(Unit) {
        AnalyticsHelper.logEvent(
            AnalyticsEvents.PANCHANG_OPEN,
            mapOf("screen" to "panchang_screen")
        )
    }

    val festivalUiItems = remember { mapFestivalUiItems() }
    val upcomingFestival = festivalUiItems.firstOrNull {
        it.status == FestivalStatus.TODAY || it.status == FestivalStatus.UPCOMING
    }
    val currentYear = remember { Calendar.getInstance().get(Calendar.YEAR) }

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
                        text = "पंचांग $currentYear",
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

                if (upcomingFestival != null) {
                    item {
                        UpcomingFestivalCard(festival = upcomingFestival)
                    }
                }

                items(festivalUiItems) { festival ->
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
private fun FestivalCard(festival: FestivalUiItem) {
    val isPastFestival = festival.status == FestivalStatus.PAST
    val cardBackgroundColor = if (isPastFestival) {
        Color(0xFFBDBDBD).copy(alpha = 0.5f)
    } else {
        Color.White.copy(alpha = 0.96f)
    }

    val borderColor = if (isPastFestival) Color(0xFF9E9E9E) else Color(0xFFFFCC80)
    val iconColor = if (isPastFestival) Color(0xFF616161) else Color(0xFFE65100)
    val titleColor = if (isPastFestival) Color(0xFF424242) else Color(0xFF4E342E)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, borderColor),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor
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
                    imageVector = if (isPastFestival) Icons.Default.CheckCircle else Icons.Default.Celebration,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = festival.festival.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = titleColor
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = {},
                    label = {
                        Text(
                            text = festival.festival.displayDate,
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

                FestivalStatusChip(status = festival.status, daysFromToday = festival.daysFromToday)
            }
        }
    }
}

@Composable
private fun FestivalStatusChip(status: FestivalStatus, daysFromToday: Int) {
    val chipData = when (status) {
        FestivalStatus.PAST -> Triple("✔ बीत चुका", Color(0xFFE0E0E0), Color(0xFF424242))
        FestivalStatus.TODAY -> Triple("आज", Color(0xFFFFCC80), Color(0xFFBF360C))
        FestivalStatus.UPCOMING -> Triple(
            if (daysFromToday == 1) "कल" else "$daysFromToday दिन बाद",
            Color(0xFFC8E6C9),
            Color(0xFF1B5E20)
        )
    }

    AssistChip(
        onClick = {},
        label = { Text(text = chipData.first, color = chipData.third) },
        colors = AssistChipDefaults.assistChipColors(containerColor = chipData.second),
        border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = chipData.third.copy(alpha = 0.35f))
    )
}

@Composable
private fun UpcomingFestivalCard(festival: FestivalUiItem) {
    val subtitle = when (festival.status) {
        FestivalStatus.TODAY -> "आज का त्योहार"
        FestivalStatus.UPCOMING -> "अगला त्योहार ${festival.daysFromToday} दिन बाद"
        FestivalStatus.PAST -> ""
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0B2)),
        border = BorderStroke(1.dp, Color(0xFFFF9800)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (festival.status == FestivalStatus.TODAY) Icons.Default.Today else Icons.Default.Schedule,
                contentDescription = null,
                tint = Color(0xFFE65100),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Upcoming Festival",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFBF360C)
                )
                Text(
                    text = "${festival.festival.name} • ${festival.festival.displayDate}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4E342E)
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF6D4C41)
                )
            }
        }
    }
}

private fun mapFestivalUiItems(): List<FestivalUiItem> {
    val today = Calendar.getInstance().apply {
        // Use noon to avoid DST edge cases while computing day difference.
        set(Calendar.HOUR_OF_DAY, 12)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    return hindiFestivals
        .map { festival ->
            val festivalDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, today.get(Calendar.YEAR))
                set(Calendar.MONTH, festival.month - 1)
                set(Calendar.DAY_OF_MONTH, festival.day)
                set(Calendar.HOUR_OF_DAY, 12)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val diffDays = TimeUnit.MILLISECONDS
                .toDays(festivalDate.timeInMillis - today.timeInMillis)
                .toInt()

            val status = when {
                diffDays < 0 -> FestivalStatus.PAST
                diffDays == 0 -> FestivalStatus.TODAY
                else -> FestivalStatus.UPCOMING
            }

            FestivalUiItem(
                festival = festival,
                status = status,
                daysFromToday = diffDays
            )
        }
        .sortedWith(
            compareBy<FestivalUiItem>(
                {
                    when (it.status) {
                        FestivalStatus.TODAY -> 0
                        FestivalStatus.UPCOMING -> 1
                        FestivalStatus.PAST -> 2
                    }
                },
                { if (it.status == FestivalStatus.PAST) Int.MAX_VALUE else it.daysFromToday }
            )
        )
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