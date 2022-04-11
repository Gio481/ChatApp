package com.example.chatapp.data.repository

import com.example.chatapp.data.datasource.local.dao.ChatDao
import com.example.chatapp.data.datasource.local.entity.ChatEntity
import com.example.chatapp.data.mapper.EntityMapper
import com.example.chatapp.domain.model.ChatDomain
import com.example.chatapp.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val entityMapper: EntityMapper<ChatEntity, ChatDomain>,
    private val dao: ChatDao,
) : ChatRepository {

    override suspend fun getAllMessages(): List<ChatDomain> =
        entityMapper.fromEntity(dao.getAllMessages())

    override suspend fun insertMessage(message: ChatDomain) =
        dao.insertMessage(entityMapper.toEntity(message))
}