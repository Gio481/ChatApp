package com.example.chatapp

import android.app.Application
import com.example.chatapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                databaseModule,
                entityMapperModule,
                viewModelModule,
                repositoryModule,
                useCaseModule)
            )
        }
    }
}