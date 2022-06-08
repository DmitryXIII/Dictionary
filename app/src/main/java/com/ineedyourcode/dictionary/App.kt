package com.ineedyourcode.dictionary

import android.app.Application
import android.content.Context
import com.ineedyourcode.dictionary.di.AppDependenciesComponent
import com.ineedyourcode.dictionary.di.DaggerAppDependenciesComponent

class App : Application() {
    val appDependenciesComponent: AppDependenciesComponent by lazy {
        DaggerAppDependenciesComponent
            .builder()
            .build()
    }
}

val Context.app: App
    get() = applicationContext as App
