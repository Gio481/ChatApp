package com.example.chatapp.util.extensions.long

import java.text.SimpleDateFormat
import java.util.*

fun Long.getStringDate(): String {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    return sdf.format(this)
}

fun Long.differenceMinutesFrom(date: Long): Long? {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    val startDate = sdf.parse(this.getStringDate())
    val endDate = sdf.parse(date.getStringDate())
    val diff = endDate?.time?.minus(startDate?.time!!)
    val seconds = diff?.div(1000)
    return seconds?.div(60)
}
