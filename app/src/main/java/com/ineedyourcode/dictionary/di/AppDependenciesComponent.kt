package com.ineedyourcode.dictionary.di

import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoriesDependenciesModule::class
])

interface AppDependenciesComponent {
    fun inject(wordSearchingFragment: WordSearchingFragment)
}