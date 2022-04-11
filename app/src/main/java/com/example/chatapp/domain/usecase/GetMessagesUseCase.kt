package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.ChatDomain

interface GetMessagesUseCase {
    suspend fun getAllMessages(): List<ChatDomain>
    suspend fun insertMessage(message: ChatDomain)
}