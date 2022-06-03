package com.ineedyourcode.dictionary.ui.wordsearching

import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

interface WordSearchingViewContract {
    fun showTranslatingResult(result: List<SearchingResult>)
    fun showTranslatingError(error: ErrorMapper)
    fun showProgress()
    fun hideProgress()
    fun hideKeyboard()
}

interface WordSearchingFragmentPresenterContract {
    fun onAttach(fragment: WordSearchingViewContract)
    fun onDetach()
    fun searchWord(word: String)
}