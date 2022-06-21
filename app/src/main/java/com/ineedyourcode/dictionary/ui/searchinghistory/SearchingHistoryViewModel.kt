package com.ineedyourcode.dictionary.ui.searchinghistory

import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity
import com.ineedyourcode.dictionary.domain.usecase.SearchingHistoryUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

class SearchingHistoryViewModel(private val gateway: SearchingHistoryUsecase) :
    ViewModelContract.BaseViewModel() {

    fun getSearchingHistory() {
        _liveData.postValue(AppState.Loading)
        try {
            _liveData.postValue(
                AppState.Success(gateway.getSearchingHistory<SearchingHistoryEntity>()))
        } catch (error: Throwable) {
            _liveData.postValue(AppState.Error(ErrorMapper.DirectString(error.message.toString())))
        }
    }

    fun addToFavorite(searchingHistoryEntity: SearchingHistoryEntity) {
        if (!searchingHistoryEntity.isFavorite) {
            gateway.addToFavorite(searchingHistoryEntity.word)
        } else {
            gateway.deleteFromFavorite(searchingHistoryEntity.word)
        }
    }
}