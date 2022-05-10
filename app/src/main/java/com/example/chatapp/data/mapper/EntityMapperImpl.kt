package com.example.chatapp.data.mapper

import com.example.chatapp.data.datasource.local.entity.ChatEntity
import com.example.chatapp.domain.model.ChatDomain

class EntityMapperImpl : EntityMapper<ChatEntity, ChatDomain> {

    override fun fromEntity(entity: List<ChatEntity>): List<ChatDomain> {
        return entity.map {
            ChatDomain(user = it.user, message = it.message, time = it.time)
        }
    }

    override fun toEntity(domain: ChatDomain): ChatEntity {
        return ChatEntity(user = domain.user, message = domain.message, time = domain.time)
    }
}