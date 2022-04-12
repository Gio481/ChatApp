package com.example.chatapp.di

import com.example.chatapp.presentation.ui.chat.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChatViewModel() }
}