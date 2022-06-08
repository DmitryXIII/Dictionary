package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase

class WordSearchingViewModelFactory(private val gateway: WordSearchingUsecase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return WordSearchingViewModel(gateway) as T
    }
}