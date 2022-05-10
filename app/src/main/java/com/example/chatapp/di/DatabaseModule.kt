package com.example.chatapp.di

import android.content.Context
import androidx.room.Room
import com.example.chatapp.data.datasource.local.ChatDatabase
import org.koin.dsl.module

private fun provideChatDatabase(context: Context): ChatDatabase {
    return Room.databaseBuilder(context, ChatDatabase::class.java, "chat.db").build()
}

private fun provideDao(db: ChatDatabase) = db.chatDao()

val databaseModule = module {
    single { provideChatDatabase(get()) }
    single { provideDao(get()) }
}