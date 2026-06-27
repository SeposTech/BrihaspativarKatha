package com.spiritual.brihaspativarkatha.util

fun formatTime(ms: Int): String {

    val totalSeconds = ms / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60

    return String.format(
        "%02d:%02d",
        minutes,
        seconds
    )
}