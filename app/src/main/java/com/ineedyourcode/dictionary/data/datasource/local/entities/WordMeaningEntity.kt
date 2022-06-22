package com.ineedyourcode.dictionary.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordMeaningEntity(
    @PrimaryKey
    val ID: String,
    val meaningOwnerWord: String,
    val imageUrl: String,
    val transcription: String,
    val translation: String,
    val note : String
)
