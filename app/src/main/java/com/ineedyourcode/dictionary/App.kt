package com.ineedyourcode.dictionary

import android.app.Application
import android.content.Context
import com.ineedyourcode.dictionary.di.appModule
import kotlinx.coroutines.Job
import org.koin.core.context.startKoin

class App : Application() {
    var job : Job? = null

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            modules(appModule)
        }
    }

    companion object {
        lateinit var instance : App
        private set
    }
}

val Context.app: App
    get() = applicationContext as App
