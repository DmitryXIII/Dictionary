package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import kotlinx.coroutines.flow.Flow

interface HistoryUsecase: FavoriteUsecase {
    fun getHistory(query: String): Flow<List<HistoryItem>>
    fun addToHistory(searchingResultItem: SearchingResultItem)
}