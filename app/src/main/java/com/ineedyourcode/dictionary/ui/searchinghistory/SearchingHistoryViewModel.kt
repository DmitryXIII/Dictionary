package com.ineedyourcode.dictionary.ui.searchinghistory

import com.ineedyourcode.dictionary.domain.usecase.HistoryUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

class SearchingHistoryViewModel(private val gateway: HistoryUsecase) :
    ViewModelContract.BaseViewModel() {

    fun getSearchingHistory() {
        _liveData.postValue(AppState.Loading)
        try {
            _liveData.postValue(
                AppState.Success(gateway.getHistory()))
        } catch (error: Throwable) {
            _liveData.postValue(AppState.Error(ErrorMapper.DirectString(error.message.toString())))
        }
    }
}