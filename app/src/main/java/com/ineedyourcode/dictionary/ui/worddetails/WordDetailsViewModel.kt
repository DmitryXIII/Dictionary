package com.ineedyourcode.dictionary.ui.worddetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.usecase.DetailsUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

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