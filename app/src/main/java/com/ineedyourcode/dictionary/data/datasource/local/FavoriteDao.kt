package com.ineedyourcode.dictionary.data.datasource.local

import androidx.room.*
import com.google.android.material.circularreveal.CircularRevealHelper
import com.ineedyourcode.dictionary.data.datasource.local.entities.FavoriteWordsEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToFavorite(word: FavoriteWordsEntity)

    @Query ("SELECT * FROM FavoriteWordsEntity")
    fun getAllFavorite() : List<FavoriteWordsEntity>

    @Delete
    fun deleteFromFavorite(word: FavoriteWordsEntity)
}