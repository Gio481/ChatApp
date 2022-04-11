package com.example.chatapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.chatapp.data.datasource.local.entity.ChatEntity

@Database(entities = [ChatEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao()
}