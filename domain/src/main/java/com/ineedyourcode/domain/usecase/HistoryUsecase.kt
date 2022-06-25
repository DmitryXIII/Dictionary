package com.ineedyourcode.domain.usecase

import com.ineedyourcode.domain.entity.HistoryItem
import com.ineedyourcode.domain.entity.SearchingResultItem
import kotlinx.coroutines.flow.Flow

interface HistoryUsecase: FavoriteUsecase {
    fun getHistory(query: String): Flow<List<HistoryItem>>
    fun addToHistory(searchingResultItem: SearchingResultItem)
}