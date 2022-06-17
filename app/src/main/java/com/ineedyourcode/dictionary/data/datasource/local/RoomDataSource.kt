package com.ineedyourcode.dictionary.data.datasource.local

import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity
import com.ineedyourcode.dictionary.domain.usecase.SearchingHistoryUsecase

class RoomDataSource(private val favoriteDao: FavoriteDao, private val historyDao: HistoryDao) :
    SearchingHistoryUsecase {
    @Suppress("UNCHECKED_CAST")
    override fun <T> getSearchingHistory(): List<T> {
        return historyDao.getSearchingHistory() as List<T>
    }

    override fun addToSearchingHistory(word: String) {
        historyDao.addToHistory(SearchingHistoryEntity(word = word))
    }

    override fun addToFavorite(word: String) {
        historyDao.addToFavorite(word)
    }
}