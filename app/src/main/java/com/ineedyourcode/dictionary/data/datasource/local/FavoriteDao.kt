package com.ineedyourcode.dictionary.data.datasource.local

import androidx.room.*
import com.google.android.material.circularreveal.CircularRevealHelper
import com.ineedyourcode.dictionary.data.datasource.local.entities.FavoriteWordsEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToFavorite(word: FavoriteWordsEntity)

    @Query("SELECT * FROM FavoriteWordsEntity WHERE word = :word")
    fun checkIsFavorite(word: String): FavoriteWordsEntity

    @Delete
    fun deleteFromFavorite(word: FavoriteWordsEntity)
}