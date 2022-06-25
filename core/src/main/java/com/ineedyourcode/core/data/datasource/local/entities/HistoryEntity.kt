package com.ineedyourcode.core.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey
    val id: String,
    val word: String,
    var isFavorite: Boolean = false,
)

