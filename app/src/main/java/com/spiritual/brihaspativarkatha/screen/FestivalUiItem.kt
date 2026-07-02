package com.spiritual.brihaspativarkatha.screen

data class FestivalItem(
    val name: String,
    val day: Int,
    val month: Int,
    val displayDate: String
)

enum class FestivalStatus {
    TODAY,
    UPCOMING,
    PAST
}

data class FestivalUiItem(
    val festival: FestivalItem,
    val status: FestivalStatus,
    val daysFromToday: Int
)

