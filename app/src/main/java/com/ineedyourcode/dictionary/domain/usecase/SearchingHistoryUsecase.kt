package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity

interface SearchingHistoryUsecase {
    fun <T> getSearchingHistory(): List<T>
    fun addToSearchingHistory(word: SearchingHistoryEntity)
    fun addToFavorite(word: String)
    fun deleteFromFavorite(word: String)
}