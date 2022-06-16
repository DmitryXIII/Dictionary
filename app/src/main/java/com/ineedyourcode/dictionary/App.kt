package com.ineedyourcode.dictionary

import android.app.Application
import android.content.Context
import com.ineedyourcode.dictionary.di.appModule
import com.ineedyourcode.dictionary.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule, roomModule)
            androidContext(this@App)
        }
    }
}

val Context.app: App
    get() = applicationContext as App
