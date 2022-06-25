package com.ineedyourcode.dictionary

import android.app.Application
import com.ineedyourcode.dictionary.di.datasourceModule
import com.ineedyourcode.dictionary.di.retrofitModule
import com.ineedyourcode.dictionary.di.roomModule
import com.ineedyourcode.dictionary.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelModule, roomModule, datasourceModule, retrofitModule)
            androidContext(this@App)
        }
    }
}
