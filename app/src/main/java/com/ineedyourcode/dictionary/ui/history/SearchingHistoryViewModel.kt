package com.ineedyourcode.dictionary.ui.history

import androidx.lifecycle.viewModelScope
import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.usecase.HistoryUsecase
import com.ineedyourcode.dictionary.ui.AppState
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

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