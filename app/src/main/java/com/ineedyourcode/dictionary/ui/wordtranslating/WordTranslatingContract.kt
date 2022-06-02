package com.ineedyourcode.dictionary.ui.wordtranslating

import com.ineedyourcode.dictionary.domain.entity.TranslationResult

interface WordTranslatingViewContract {
    fun showTranslatingResult(result: List<TranslationResult>)
    fun showTranslatingError(error: String)
}

interface WordTranslatingFragmentPresenterContract {
    fun onAttach(fragment: WordTranslatingViewContract)
    fun onDetach()
    fun searchWord(word: String)
}