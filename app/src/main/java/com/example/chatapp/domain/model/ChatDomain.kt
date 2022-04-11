package com.example.chatapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatDomain(
    val id:Long,
    val user:String,
    val message: String,
    val time: String,
) : Parcelable