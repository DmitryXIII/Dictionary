package com.ineedyourcode.dictionary

import android.app.Application
import com.ineedyourcode.dictionary.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                detailsModule,
                roomModule,
                datasourceModule,
                retrofitModule,
                historyModule,
                searchingModule)
            androidContext(this@App)
        }
    }
}
