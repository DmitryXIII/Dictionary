package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import io.reactivex.rxjava3.kotlin.subscribeBy

private const val ALPHA_KEY = "ALPHA"
private const val ALPHA_INITIAL_VALUE = 1f

class WordSearchingViewModel(
    private val gateway: WordSearchingUsecase,
    private val savedStateHandle: SavedStateHandle,
) :
    WordSearchingViewModelContract.BaseViewModel() {
    override val liveData: MutableLiveData<AppState> = MutableLiveData()

    override fun searchWord(word: String) {
        if (word.isNotEmpty()) {
            liveData.postValue(AppState.Loading)

            gateway.search(word).subscribeBy(
                onSuccess = {
                    liveData.postValue(AppState.Success(it))
                },
                onError = {
                    when (it.message) {
                        ResponseCodes.INVALID_REQUEST.code -> {
                            liveData.postValue(AppState.Error(
                                ErrorMapper.StringResource(ResponseCodes.INVALID_REQUEST)))
                        }

                        else -> {
                            liveData.postValue(AppState.Error(
                                ErrorMapper.DirectString(it.message.toString())))
                        }
                    }
                })
        } else {
            liveData.postValue(AppState.Error(
                ErrorMapper.StringResource(ResponseCodes.EMPTY_REQUEST)))
        }
    }

    val lottieState: LiveData<Float> = savedStateHandle.getLiveData(ALPHA_KEY, ALPHA_INITIAL_VALUE)

    fun saveLottieVisibilityState(alpha: Float) {
        savedStateHandle.set(ALPHA_KEY, alpha)
    }
}