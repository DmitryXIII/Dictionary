package com.ineedyourcode.domain.entity

data class WordMeaning(
    val ID : String,
    val imageUrl: String,
    val transcription: String,
    val translation: String,
    val note: String
)