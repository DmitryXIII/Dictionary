package com.ineedyourcode.dictionary.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteWordsEntity(
    @PrimaryKey
    val word: String,
)
