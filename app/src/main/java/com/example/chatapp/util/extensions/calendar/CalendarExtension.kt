package com.example.chatapp.util.extensions.calendar

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.getFormattedDate(): String {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    return sdf.format(this.time)
}