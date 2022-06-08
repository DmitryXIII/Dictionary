package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.DomainCallback
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

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
        if (word.isNotEmpty()) {
            liveData.postValue(AppState.Loading)

            gateway.search(word, object : DomainCallback<List<SearchingResult>> {
                override fun onSuccess(result: List<SearchingResult>) {
                    liveData.postValue(AppState.Success(result))
                }

                override fun onError(error: String) {
                    when (error) {
                        ResponseCodes.INVALID_REQUEST.code -> {
                            liveData.postValue(AppState.Error(
                                ErrorMapper.StringResource(ResponseCodes.INVALID_REQUEST)))
                        }

                        else -> {
                            liveData.postValue(AppState.Error(
                                ErrorMapper.DirectString(error)))
                        }
                    }
                }
            })
        } else {
            liveData.postValue(AppState.Error(
                ErrorMapper.StringResource(ResponseCodes.EMPTY_REQUEST)))
        }
    }

    fun saveLottieVisibilityState(alpha: Float) {
        savedStateHandle.set(ALPHA_KEY, alpha)
    }
}