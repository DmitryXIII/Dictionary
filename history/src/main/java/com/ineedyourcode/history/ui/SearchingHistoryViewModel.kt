package com.ineedyourcode.history.ui

import com.ineedyourcode.core.ui.ViewModelContract
import com.ineedyourcode.domain.entity.HistoryItem
import com.ineedyourcode.domain.usecase.HistoryUsecase
import kotlinx.coroutines.flow.Flow

class SearchingHistoryViewModel(private val gateway: HistoryUsecase) :
    ViewModelContract.BaseViewModel() {

    fun searchInHistory(query: String) : Flow<List<HistoryItem>> {
        return gateway.getHistory(query)
    }

    fun updateFavorite(historyItem: HistoryItem) {
        historyItem.isFavorite = !historyItem.isFavorite
        gateway.updateFavorite(historyItem)
    }
}