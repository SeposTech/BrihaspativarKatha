package com.spiritual.brihaspativarkatha.data.analytics.model

data class AartiModel(
    val title: String,
    val resId: Int
)
data class AartiDay(
    val day: String,
    val god: String,
    val aartiList: List<AartiModel>
)