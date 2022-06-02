package com.ineedyourcode.dictionary.ui.wordtranslating

import com.ineedyourcode.dictionary.domain.entity.TranslatingResult

interface WordTranslatingFragmentContract {
    fun showTranslatingResult(result: TranslatingResult)
}

interface WordTranslatingFragmentPresenterContract {
    fun onAttach(fragment: WordTranslatingFragmentContract)
    fun onDetach()
    fun searchWord(word: String)
}