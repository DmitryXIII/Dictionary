package com.ineedyourcode.worddetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ineedyourcode.core.ui.AppState
import com.ineedyourcode.core.ui.ViewModelContract
import com.ineedyourcode.core.ui.uils.ErrorMapper
import com.ineedyourcode.domain.entity.HistoryItem
import com.ineedyourcode.domain.usecase.DetailsUsecase

class WordDetailsViewModel(private val gateway: DetailsUsecase) :
    ViewModelContract.BaseViewModel() {

    private val _historyItemLiveData = MutableLiveData<HistoryItem>()

    fun getWordMeanings(wordId: String): LiveData<AppState> {
        _liveData.postValue(AppState.Loading)
        try {
            _liveData.postValue(AppState.Success(gateway.getWordMeanings(wordId)))
        } catch (error: Throwable) {
            _liveData.postValue(AppState.Error(ErrorMapper.DirectString(error.message.toString())))
        }
        return _liveData
    }

    fun updateFavorite(historyItem: HistoryItem) {
        gateway.updateFavorite(historyItem)
    }

    fun getHistoryItem(word: String) : LiveData<HistoryItem> {
        _historyItemLiveData.postValue(gateway.getHistoryItem(word))
        return _historyItemLiveData
    }
}