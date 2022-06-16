package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import kotlinx.coroutines.launch

private const val ALPHA_KEY = "ALPHA"
private const val ALPHA_INITIAL_VALUE = 1f

class WordSearchingViewModel(
    private val gateway: WordSearchingUsecase,
    private val savedStateHandle: SavedStateHandle,
) :
    WordSearchingViewModelContract.BaseViewModel() {
    override val liveData: MutableLiveData<AppState> = MutableLiveData()
    val lottieState: LiveData<Float> = savedStateHandle.getLiveData(ALPHA_KEY, ALPHA_INITIAL_VALUE)

    override fun searchWord(word: String) {
        liveData.postValue(AppState.Loading)

        viewModelScope.launch {
            try {
                val result = gateway.search(word)
                if (result.isEmpty()) {
                    liveData.postValue(AppState.Error(
                        ErrorMapper.StringResource(ResponseCodes.INVALID_REQUEST)))
                } else {
                    liveData.postValue(AppState.Success(result))
                }
            } catch (error: Throwable) {
                liveData.postValue(AppState.Error(
                    ErrorMapper.DirectString(error.message.toString())))
            }
        }
    }

    fun saveLottieVisibilityState(alpha: Float) {
        savedStateHandle.set(ALPHA_KEY, alpha)
    }
}