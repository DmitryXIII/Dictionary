package com.ineedyourcode.dictionary.ui.wordtranslating

import com.ineedyourcode.dictionary.domain.entity.TranslationResult

interface WordTranslatingViewContract {
    val errorMapper: ErrorMapper
    fun showTranslatingResult(result: List<TranslationResult>)
    fun showTranslatingError(error: String)
    fun showProgress()
    fun hideProgress()
    fun hideKeyboard()
}

interface WordTranslatingFragmentPresenterContract {
    fun onAttach(fragment: WordTranslatingViewContract)
    fun onDetach()
    fun searchWord(word: String)
}