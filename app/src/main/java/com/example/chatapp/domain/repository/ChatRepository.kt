package com.example.chatapp.domain.repository

import com.example.chatapp.domain.model.ChatDomain

interface ChatRepository {
    suspend fun getAllMessages(): List<ChatDomain>
    suspend fun insertMessage(message: ChatDomain)
    suspend fun getTime(): Long?
}