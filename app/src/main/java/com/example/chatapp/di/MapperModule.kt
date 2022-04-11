package com.example.chatapp.di

import com.example.chatapp.data.datasource.local.entity.ChatEntity
import com.example.chatapp.data.mapper.EntityMapper
import com.example.chatapp.data.mapper.EntityMapperImpl
import com.example.chatapp.domain.model.ChatDomain
import org.koin.dsl.module

val entityMapperModule = module {
    single<EntityMapper<ChatEntity, ChatDomain>> { EntityMapperImpl() }
}