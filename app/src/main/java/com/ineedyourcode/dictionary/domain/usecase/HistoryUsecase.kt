package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning

interface HistoryUsecase {
    fun getHistory(): List<HistoryItem>
    fun addToFavorite(word: String)
    fun deleteFromFavorite(word: String)
    fun addToHistory(searchingResultItem: SearchingResultItem)
}