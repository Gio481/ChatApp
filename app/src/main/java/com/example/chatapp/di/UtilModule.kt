package com.example.chatapp.di

import com.example.chatapp.presentation.ui.chat.adapter.util.RecyclerViewUtilClass
import com.example.chatapp.presentation.ui.chat.adapter.util.RecyclerViewUtilClassImpl
import org.koin.dsl.module

val utilModule = module {
    single<RecyclerViewUtilClass> { RecyclerViewUtilClassImpl() }
}