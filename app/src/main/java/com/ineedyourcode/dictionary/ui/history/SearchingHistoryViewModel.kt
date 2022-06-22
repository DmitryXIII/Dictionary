package com.ineedyourcode.dictionary.ui.history

import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.usecase.HistoryUsecase
import com.ineedyourcode.dictionary.ui.ViewModelContract
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