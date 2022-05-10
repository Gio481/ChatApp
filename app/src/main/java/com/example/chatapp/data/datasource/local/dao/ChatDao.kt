package com.example.chatapp.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chatapp.data.datasource.local.entity.ChatEntity

@Dao
interface ChatDao {
    @Query("SELECT * FROM chat")
    suspend fun getAllMessages(): List<ChatEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatEntity)

    @Query("SELECT time FROM chat  ORDER BY time DESC LIMIT 1")
    suspend fun getTime(): Long?

}