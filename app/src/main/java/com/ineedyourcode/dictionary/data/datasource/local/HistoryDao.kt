package com.ineedyourcode.dictionary.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity

@Dao
interface HistoryDao {
    @Query ("SELECT * FROM SearchingHistoryEntity")
    fun getSearchingHistory() : List<SearchingHistoryEntity>

    @Insert
    fun addToHistory(historyEntity: SearchingHistoryEntity)

    @Query("UPDATE SearchingHistoryEntity SET isFavorite = 1 WHERE translation = :word")
    fun addToFavorite(word: String)

    @Query("UPDATE SearchingHistoryEntity SET isFavorite = 0 WHERE translation = :word")
    fun deleteFromFavorite(word: String)
}