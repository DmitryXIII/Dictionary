package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

interface WordSearchingViewContract {
    fun showTranslatingResult(result: List<SearchingResult>)
    fun showTranslatingError(error: ErrorMapper)
    fun renderData(state: WordSearchingState)
    fun showProgress()
    fun hideProgress()
    fun hideKeyboard()
}

interface WordSearchingViewModelContract {
    abstract class BaseViewModel : ViewModel() {
        abstract val liveData: LiveData<WordSearchingState>
        abstract fun searchWord(word: String)
        fun getData() = liveData
    }
}