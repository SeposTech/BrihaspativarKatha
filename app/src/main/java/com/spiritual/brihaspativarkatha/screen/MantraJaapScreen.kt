package com.spiritual.brihaspativarkatha.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

private val SpiritualBackground = Color(0xFFFFF8E8)
private val SpiritualCard = Color(0xFFFFFCF5)
private val SpiritualGold = Color(0xFFD88A0A)
private val SpiritualGoldLight = Color(0xFFFFE0A3)
private val SpiritualBrown = Color(0xFF5A3218)
private val SpiritualText = Color(0xFF33271F)
private val SpiritualSecondary = Color(0xFF806C5A)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MantraJaapScreen(
    currentCount: Int = 0,
    initialTarget: Int = 108,
    onBack: () -> Unit = {},
    onHistoryClick: () -> Unit = {},
    onCountChanged: (Int) -> Unit = {},
    onTargetChanged: (Int) -> Unit = {}
) {

    var count by remember { mutableIntStateOf(currentCount) }
    var target by remember { mutableIntStateOf(initialTarget) }
    var showCustomDialog by remember { mutableStateOf(false) }

    val haptic = LocalHapticFeedback.current

    val progress = if (target > 0) {
        (count.toFloat() / target.toFloat()).coerceIn(0f, 1f)
    } else {
        0f
    }

    val percentage = (progress * 100).roundToInt()

    val presetTargets = listOf(21, 108, 216, 1008, 5000)

    fun updateCount(newCount: Int) {
        count = newCount.coerceIn(0, target)
        onCountChanged(count)
    }

    fun selectTarget(newTarget: Int) {
        target = newTarget
        count = 0

        onTargetChanged(target)
        onCountChanged(count)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = SpiritualBackground
        ) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "मंत्र जाप",
                        fontWeight = FontWeight.Bold,
                        color = SpiritualBrown
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = SpiritualBrown
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = onHistoryClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = "Jaap History",
                            tint = SpiritualBrown
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = SpiritualBackground
                )
            )

            // ---------------------------------------------------------
            // MAIN CONTENT
            // ---------------------------------------------------------

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 24.dp
                ),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {



                // -----------------------------------------------------
                // MANTRA CARD
                // -----------------------------------------------------

                item {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(26.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = SpiritualCard
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 18.dp,
                                    vertical = 22.dp
                                ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector = Icons.Default.Spa,
                                    contentDescription = null,
                                    tint = SpiritualGold,
                                    modifier = Modifier.size(20.dp)
                                )

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )

                                Text(
                                    text = "मंत्र",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SpiritualGold
                                )

                                Spacer(
                                    modifier = Modifier.width(8.dp)
                                )

                                Icon(
                                    imageVector = Icons.Default.Spa,
                                    contentDescription = null,
                                    tint = SpiritualGold,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(18.dp)
                            )

                            Text(
                                text = "ॐ नमो भगवते वासुदेवाय नमः",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                lineHeight = 34.sp,
                                fontWeight = FontWeight.Bold,
                                color = SpiritualText
                            )

                            Spacer(
                                modifier = Modifier.height(18.dp)
                            )

                            HorizontalDivider(
                                color = SpiritualGoldLight,
                                thickness = 1.dp
                            )

                            Spacer(
                                modifier = Modifier.height(18.dp)
                            )

                            Text(
                                text = "जाप लक्ष्य",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = SpiritualBrown
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            // -------------------------------------------------
                            // TARGET OPTIONS
                            // -------------------------------------------------

                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                items(presetTargets) { preset ->

                                    TargetChip(
                                        text = preset.toString(),
                                        selected = target == preset,
                                        onClick = {
                                            selectTarget(preset)
                                        }
                                    )
                                }

                                item {

                                    TargetChip(
                                        text = "Custom",
                                        selected = !presetTargets.contains(target),
                                        showEditIcon = true,
                                        onClick = {
                                            showCustomDialog = true
                                        }
                                    )
                                }
                            }

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "या अपनी इच्छा अनुसार कोई भी संख्या चुनें",
                                fontSize = 12.sp,
                                color = SpiritualSecondary
                            )

                            Spacer(
                                modifier = Modifier.height(28.dp)
                            )

                            // -------------------------------------------------
                            // COUNTER
                            // -------------------------------------------------

                            Box(
                                modifier = Modifier
                                    .size(250.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 2.dp,
                                        color = SpiritualGoldLight,
                                        shape = CircleShape
                                    )
                                    .background(
                                        Brush.radialGradient(
                                            colors = listOf(
                                                Color.White,
                                                Color(0xFFFFFAEF)
                                            )
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "जाप संख्या",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SpiritualGold
                                    )

                                    Spacer(
                                        modifier = Modifier.height(8.dp)
                                    )

                                    Text(
                                        text = count.toString(),
                                        fontSize = 64.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SpiritualBrown
                                    )

                                    Text(
                                        text = "/ $target",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = SpiritualBrown
                                    )

                                    Spacer(
                                        modifier = Modifier.height(8.dp)
                                    )

                                    Text(
                                        text = "$percentage% पूर्ण",
                                        fontSize = 13.sp,
                                        color = SpiritualSecondary
                                    )
                                }
                            }

                            Spacer(
                                modifier = Modifier.height(18.dp)
                            )

                            // -------------------------------------------------
                            // PLUS MINUS
                            // -------------------------------------------------

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                CounterButton(
                                    icon = Icons.Default.Remove,
                                    enabled = count > 0,
                                    onClick = {
                                        if (count > 0) {
                                            haptic.performHapticFeedback(
                                                HapticFeedbackType.TextHandleMove
                                            )

                                            updateCount(count - 1)
                                        }
                                    }
                                )

                                Spacer(
                                    modifier = Modifier.width(80.dp)
                                )

                                CounterButton(
                                    icon = Icons.Default.Add,
                                    enabled = count < target,
                                    onClick = {
                                        if (count < target) {
                                            haptic.performHapticFeedback(
                                                HapticFeedbackType.LongPress
                                            )

                                            updateCount(count + 1)
                                        }
                                    }
                                )
                            }

                            Spacer(
                                modifier = Modifier.height(20.dp)
                            )

                            // -------------------------------------------------
                            // MAIN JAAP BUTTON
                            // -------------------------------------------------

                            Box(
                                modifier = Modifier
                                    .size(130.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0xFFFFB52E),
                                                Color(0xFFD77F00)
                                            )
                                        )
                                    )
                                    .border(
                                        width = 3.dp,
                                        color = Color(0xFFFFD98A),
                                        shape = CircleShape
                                    )
                                    .clickable(
                                        enabled = count < target
                                    ) {

                                        haptic.performHapticFeedback(
                                            HapticFeedbackType.LongPress
                                        )

                                        updateCount(count + 1)
                                    },
                                contentAlignment = Alignment.Center
                            ) {

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Text(
                                        text = "ॐ",
                                        fontSize = 26.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = if (count >= target) {
                                            "पूर्ण 🙏"
                                        } else {
                                            "जाप करें"
                                        },
                                        fontSize = 19.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(
                                modifier = Modifier.height(14.dp)
                            )

                            Text(
                                text = "गिनती के लिए बीच में बटन दबाएँ",
                                fontSize = 13.sp,
                                color = SpiritualSecondary,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                text = "(हर जाप पर हल्का कंपन महसूस होगा)",
                                fontSize = 12.sp,
                                color = SpiritualSecondary,
                                textAlign = TextAlign.Center
                            )

                            Spacer(
                                modifier = Modifier.height(24.dp)
                            )

                            // -------------------------------------------------
                            // PROGRESS
                            // -------------------------------------------------

                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(18.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFFFF9ED)
                                )
                            ) {

                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {

                                    Text(
                                        text = "प्रगति",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = SpiritualBrown
                                    )

                                    Spacer(
                                        modifier = Modifier.height(12.dp)
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        LinearProgressIndicator(
                                            progress = { progress },
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(9.dp)
                                                .clip(RoundedCornerShape(10.dp)),
                                            color = SpiritualGold,
                                            trackColor = SpiritualGoldLight
                                        )

                                        Spacer(
                                            modifier = Modifier.width(12.dp)
                                        )

                                        Text(
                                            text = "$percentage%",
                                            fontWeight = FontWeight.Bold,
                                            color = SpiritualGold
                                        )
                                    }

                                    Spacer(
                                        modifier = Modifier.height(8.dp)
                                    )

                                    Text(
                                        text = "$count / $target पूरा हुआ",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        fontSize = 14.sp,
                                        color = SpiritualSecondary
                                    )
                                }
                            }
                        }
                    }
                }

                // ---------------------------------------------------------
                // HISTORY BUTTON
                // ---------------------------------------------------------

                item {

                    OutlinedButton(
                        onClick = onHistoryClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        shape = RoundedCornerShape(18.dp),
                        border = BorderStroke(
                            1.dp,
                            SpiritualGoldLight
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = SpiritualBrown
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        Text(
                            text = "जाप इतिहास देखें",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // ---------------------------------------------------------
                // LOCAL STORAGE NOTE
                // ---------------------------------------------------------

                item {

                    Text(
                        text = "ⓘ आपकी जाप हिस्ट्री आपके डिवाइस पर सुरक्षित रखी जाती है।\nऐप uninstall या data clear करने पर history हट सकती है।",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp,
                        lineHeight = 17.sp,
                        color = SpiritualSecondary
                    )
                }
            }
            }
        }

    }

    // ---------------------------------------------------------------------
    // CUSTOM TARGET DIALOG
    // ---------------------------------------------------------------------

    if (showCustomDialog) {

        CustomTargetDialog(
            onDismiss = {
                showCustomDialog = false
            },
            onConfirm = { customTarget ->

                selectTarget(customTarget)

                showCustomDialog = false
            }
        )
    }
}


@Composable
private fun TargetChip(
    text: String,
    selected: Boolean,
    showEditIcon: Boolean = false,
    onClick: () -> Unit
) {

    val backgroundColor = if (selected) {
        SpiritualGold
    } else {
        SpiritualCard
    }

    val textColor = if (selected) {
        Color.White
    } else {
        SpiritualText
    }

    Surface(
        modifier = Modifier
            .height(48.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable {
                onClick()
            }
            .border(
                width = 1.dp,
                color = if (selected) {
                    SpiritualGold
                } else {
                    SpiritualGoldLight
                },
                shape = RoundedCornerShape(14.dp)
            ),
        color = backgroundColor
    ) {

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            if (showEditIcon) {

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = textColor
                )

                Spacer(
                    modifier = Modifier.width(5.dp)
                )
            }

            Text(
                text = text,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

@Composable
private fun CounterButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    enabled: Boolean,
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier.size(58.dp),
        shape = RoundedCornerShape(18.dp),
        color = if (enabled) {
            SpiritualCard
        } else {
            Color(0xFFF1EBDD)
        },
        border = BorderStroke(
            width = 1.dp,
            color = SpiritualGoldLight
        ),
        onClick = onClick,
        enabled = enabled
    ) {

        Box(
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = if (enabled) {
                    SpiritualBrown
                } else {
                    SpiritualSecondary.copy(alpha = 0.4f)
                }
            )
        }
    }
}

@Composable
private fun CustomTargetDialog(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {

    var input by remember {
        mutableStateOf("")
    }

    val value = input.toIntOrNull()

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text(
                text = "Custom जाप लक्ष्य",
                fontWeight = FontWeight.Bold,
                color = SpiritualBrown
            )
        },

        text = {

            Column {

                Text(
                    text = "अपनी इच्छा अनुसार जाप की संख्या चुनें।",
                    color = SpiritualSecondary
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                OutlinedTextField(
                    value = input,
                    onValueChange = { newValue ->

                        if (newValue.all { it.isDigit() }) {
                            input = newValue
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text("जाप संख्या")
                    },
                    placeholder = {
                        Text("जैसे 2500")
                    }
                )
            }
        },

        confirmButton = {

            Button(
                onClick = {
                    value?.let {
                        if (it > 0) {
                            onConfirm(it)
                        }
                    }
                },
                enabled = value != null && value > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = SpiritualGold
                )
            ) {
                Text("सेट करें")
            }
        },

        dismissButton = {

            OutlinedButton(
                onClick = onDismiss
            ) {
                Text("रद्द करें")
            }
        },

        containerColor = SpiritualCard,
        shape = RoundedCornerShape(24.dp)
    )
}

@androidx.compose.ui.tooling.preview.Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun MantraJaapScreenPreview() {

    MaterialTheme {

        MantraJaapScreen()
    }
}