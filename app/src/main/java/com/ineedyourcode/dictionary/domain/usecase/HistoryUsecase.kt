package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning
import kotlinx.coroutines.flow.Flow

interface HistoryUsecase {
    fun getHistory(query: String): Flow<List<HistoryItem>>
    fun updateFavorite(historyItem: HistoryItem)
    fun deleteFromFavorite(word: String)
    fun addToHistory(searchingResultItem: SearchingResultItem)
}