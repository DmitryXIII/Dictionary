package com.ineedyourcode.core.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ineedyourcode.core.data.datasource.local.entities.WordMeaningEntity

@Dao
interface WordMeaningDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMeaning(wordMeaning: WordMeaningEntity)

    @Query("SELECT * FROM WordMeaningEntity WHERE meaningOwnerWord = :meaningOwnerWord")
    fun getMeaningByOwnerId(meaningOwnerWord: String): List<WordMeaningEntity>

    @Query("DELETE FROM WordMeaningEntity")
    fun clearWordMeaningEntity()
}