package com.example.chatapp.di

import com.example.chatapp.domain.usecase.GetMessageUseCaseImpl
import com.example.chatapp.domain.usecase.GetMessagesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetMessagesUseCase> { GetMessageUseCaseImpl(get()) }
}