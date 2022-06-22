package com.ineedyourcode.dictionary.ui.history

import com.ineedyourcode.dictionary.domain.usecase.HistoryUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

class SearchingHistoryViewModel(private val gateway: HistoryUsecase) :
    ViewModelContract.BaseViewModel() {

    fun searchInHistory(query: String) {
        _liveData.postValue(AppState.Loading)
        try {
            if (query.isNotEmpty()) {

                _liveData.postValue(
                    AppState.Success(gateway.getHistory()
                        .filter { historyItem -> historyItem.word.contains(query) }))
            } else {
                _liveData.postValue(AppState.Success(gateway.getHistory()))
            }
        } catch (error: Throwable) {
            _liveData.postValue(AppState.Error(ErrorMapper.DirectString(error.message.toString())))
        }
    }
}