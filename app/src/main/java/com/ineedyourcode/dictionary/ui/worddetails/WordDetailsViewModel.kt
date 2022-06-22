package com.ineedyourcode.dictionary.ui.worddetails

import androidx.lifecycle.LiveData
import com.ineedyourcode.dictionary.domain.usecase.DetailsUsecase
import com.ineedyourcode.dictionary.domain.usecase.GatewayUsecase
import com.ineedyourcode.dictionary.domain.usecase.HistoryUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

class WordDetailsViewModel(private val gateway: DetailsUsecase) :
    ViewModelContract.BaseViewModel() {

    fun getWordMeanings(wordId: String): LiveData<AppState> {
        _liveData.postValue(AppState.Loading)
        try {
            _liveData.postValue(AppState.Success(gateway.getWordMeanings(wordId)))
        } catch (error: Throwable) {
            _liveData.postValue(AppState.Error(ErrorMapper.DirectString(error.message.toString())))
        }
        return _liveData
    }
}