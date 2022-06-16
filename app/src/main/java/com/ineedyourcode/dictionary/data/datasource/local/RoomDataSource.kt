package com.ineedyourcode.dictionary.data.datasource.local

import com.ineedyourcode.dictionary.data.datasource.local.entities.FavoriteWordsEntity

class RoomDataSource(private val favoriteDao: FavoriteDao, private val historyDao: HistoryDao) {
    fun getAllFavorite(): List<FavoriteWordsEntity> {
        return favoriteDao.getAllFavorite()
    }
}