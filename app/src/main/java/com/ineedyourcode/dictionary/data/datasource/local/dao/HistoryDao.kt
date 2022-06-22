package com.ineedyourcode.dictionary.data.datasource.local.dao

import androidx.room.*
import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity WHERE word LIKE '%' || :query || '%' ")
    fun getHistory(query: String): Flow<List<HistoryEntity>>

    @Query("SELECT * FROM HistoryEntity WHERE word LIKE :word ")
    fun getHistoryItem(word: String): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToHistory(historyEntity: HistoryEntity)

    @Update
    fun updateFavorite(historyEntity: HistoryEntity)

    @Query("DELETE FROM HistoryEntity")
    fun clearHistory()
}