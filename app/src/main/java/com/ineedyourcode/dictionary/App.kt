package com.ineedyourcode.dictionary

import android.app.Application
import com.ineedyourcode.dictionary.ui.wordtranslating.WordTranslatingFragmentPresenter
import com.ineedyourcode.dictionary.ui.wordtranslating.WordTranslatingFragmentPresenterContract

class App : Application() {
    companion object {
        val wordTranslatePresenter: WordTranslatingFragmentPresenterContract by lazy {
            WordTranslatingFragmentPresenter()
        }
    }
}