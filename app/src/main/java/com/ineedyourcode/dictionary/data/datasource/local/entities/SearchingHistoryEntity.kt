package com.ineedyourcode.dictionary.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchingHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String,
)

