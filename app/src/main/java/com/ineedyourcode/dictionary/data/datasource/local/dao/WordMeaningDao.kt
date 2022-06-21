package com.ineedyourcode.dictionary.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ineedyourcode.dictionary.data.datasource.local.entities.WordMeaningEntity

@Dao
interface WordMeaningDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMeaning(wordMeaning: WordMeaningEntity)

    @Query("SELECT * FROM WordMeaningEntity WHERE meaningOwnerId = :meaningOwnerId")
    fun getMeaningByOwnerId(meaningOwnerId: String): List<WordMeaningEntity>

    @Query("DELETE FROM WordMeaningEntity")
    fun clearWordMeaningEntity()
}