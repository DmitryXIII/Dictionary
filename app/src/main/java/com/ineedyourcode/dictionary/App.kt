package com.ineedyourcode.dictionary

import android.app.Application
import android.content.Context
import com.ineedyourcode.dictionary.di.appModule
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}

val Context.app: App
    get() = applicationContext as App
