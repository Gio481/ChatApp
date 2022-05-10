package com.example.chatapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatDomain(
    val id: Long? = null,
    val user: String,
    val message: String,
    val time: Long?,
) : Parcelable