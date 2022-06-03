package com.ineedyourcode.dictionary

import android.app.Application
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingFragmentPresenter
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingFragmentPresenterContract

class App : Application() {
    companion object {
        val wordTranslatePresenter: WordSearchingFragmentPresenterContract by lazy {
            WordSearchingFragmentPresenter()
        }
    }
}