package com.ineedyourcode.dictionary.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    fun getSearchingHistory(): List<HistoryEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToHistory(historyEntity: HistoryEntity)

    @Query("UPDATE HistoryEntity SET isFavorite = 1 WHERE word = :word")
    fun addToFavorite(word: String)

    @Query("UPDATE HistoryEntity SET isFavorite = 0 WHERE word = :word")
    fun deleteFromFavorite(word: String)

    @Query("DELETE FROM HistoryEntity")
    fun clearHistory()
}