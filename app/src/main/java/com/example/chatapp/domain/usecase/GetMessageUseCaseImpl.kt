package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.domain.repository.ChatRepository

class GetMessageUseCaseImpl(private val repository: ChatRepository) : GetMessagesUseCase {
    override suspend fun getAllMessages(): List<ChatDomain> = repository.getAllMessages()
    override suspend fun insertMessage(message: ChatDomain) = repository.insertMessage(message)
    override suspend fun getTime(): Long? = repository.getTime()
}