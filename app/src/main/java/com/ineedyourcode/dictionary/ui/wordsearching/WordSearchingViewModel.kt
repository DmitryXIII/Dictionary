package com.ineedyourcode.dictionary.ui.wordsearching

import androidx.lifecycle.MutableLiveData
import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import io.reactivex.rxjava3.kotlin.subscribeBy

class WordSearchingViewModel(private val gateway: WordSearchingUsecase) :
    WordSearchingViewModelContract.BaseViewModel() {
    override val liveData: MutableLiveData<WordSearchingState> = MutableLiveData()

    override fun searchWord(word: String) {
        if (word.isNotEmpty()) {
            liveData.postValue(WordSearchingState.Loading)

            gateway.search(word).subscribeBy(
                onSuccess = {
                    liveData.postValue(WordSearchingState.Success(it))
                },
                onError = {
                    when (it.message) {
                        ResponseCodes.INVALID_REQUEST.code -> {
                            liveData.postValue(WordSearchingState.Error(
                                ErrorMapper.StringResource(ResponseCodes.INVALID_REQUEST)))
                        }

                        else -> {
                            liveData.postValue(WordSearchingState.Error(
                                ErrorMapper.DirectString(it.message.toString())))
                        }
                    }
                })
        } else {
            liveData.postValue(WordSearchingState.Error(
                ErrorMapper.StringResource(ResponseCodes.EMPTY_REQUEST)))
        }
    }
}