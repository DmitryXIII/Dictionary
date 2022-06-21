package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.usecase.GatewayUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import kotlinx.coroutines.launch

private const val ALPHA_KEY = "ALPHA"
private const val ALPHA_INITIAL_VALUE = 1f

class WordSearchingViewModel(
    private val gateway: GatewayUsecase,
    private val savedStateHandle: SavedStateHandle,
) :
    ViewModelContract.BaseViewModel() {
    val lottieState: LiveData<Float> = savedStateHandle.getLiveData(ALPHA_KEY, ALPHA_INITIAL_VALUE)

    fun searchWord(word: String) {
        _liveData.postValue(AppState.Loading)

        viewModelScope.launch {
            try {
                val result = gateway.searchInDictionary(word)
                if (result.isEmpty()) {
                    _liveData.postValue(AppState.Error(
                        ErrorMapper.StringResource(ResponseCodes.INVALID_REQUEST)))
                } else {
                    _liveData.postValue(AppState.Success(result))
                }
            } catch (error: Throwable) {
                _liveData.postValue(AppState.Error(
                    ErrorMapper.DirectString(error.message.toString())))
            }
        }
    }

    fun addToHistory(searchingResultItem: SearchingResultItem) {
        gateway.addToHistory(searchingResultItem)
    }

    fun saveLottieVisibilityState(alpha: Float) {
        savedStateHandle.set(ALPHA_KEY, alpha)
    }
}