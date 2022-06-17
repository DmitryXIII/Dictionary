package com.ineedyourcode.dictionary.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ineedyourcode.dictionary.data.datasource.local.entities.FavoriteWordsEntity
import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity

@Dao
interface HistoryDao {
    @Query ("SELECT * FROM SearchingHistoryEntity")
    fun getSearchingHistory() : List<SearchingHistoryEntity>

    @Insert
    fun addToHistory(historyEntity: SearchingHistoryEntity)

    @Query("UPDATE SearchingHistoryEntity SET isFavorite = 1 WHERE word = :word")
    fun addToFavorite(word: String)
}