package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

interface WordSearchingViewModelContract {
    abstract class BaseViewModel : ViewModel() {
        abstract val liveData: LiveData<WordSearchingState>
        abstract fun searchWord(word: String)
        fun getData() = liveData
    }
}