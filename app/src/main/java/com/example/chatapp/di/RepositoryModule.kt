package com.example.chatapp.di

import com.example.chatapp.data.repository.ChatRepositoryImpl
import com.example.chatapp.domain.repository.ChatRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ChatRepository> { ChatRepositoryImpl(get(), get()) }
}