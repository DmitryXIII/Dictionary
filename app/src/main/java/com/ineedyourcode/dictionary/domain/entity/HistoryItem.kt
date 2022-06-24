package com.ineedyourcode.dictionary.domain.entity

data class HistoryItem(
    val ID: String,
    val word: String,
    var isFavorite: Boolean = false,
)