package com.example.chatapp.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class ChatEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val user: String,
    val message: String,
    val time: Long?,
)