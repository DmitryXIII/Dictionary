package com.ineedyourcode.dictionary.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordMeaningEntity(
    @PrimaryKey
    val id: String,
    val meaningOwnerId: String,
    val imageUrl: String,
    val transcription: String,
    val translation: String,
    val note : String
)
