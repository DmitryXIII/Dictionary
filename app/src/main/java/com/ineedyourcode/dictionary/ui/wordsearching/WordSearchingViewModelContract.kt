package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ineedyourcode.dictionary.ui.AppState

interface WordSearchingViewModelContract {
    abstract class BaseViewModel : ViewModel() {
        abstract val liveData: LiveData<AppState>
        abstract fun searchWord(word: String)
        fun getData() = liveData
    }
}