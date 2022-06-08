package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val ALPHA_KEY = "ALPHA"
private const val ALPHA_INITIAL_VALUE = 1f

class StateSavingViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val data: LiveData<Float> = savedStateHandle.getLiveData(ALPHA_KEY, ALPHA_INITIAL_VALUE)

    fun saveLottieVisibilityState(alpha: Float) {
        savedStateHandle.set(ALPHA_KEY, alpha)
    }
}