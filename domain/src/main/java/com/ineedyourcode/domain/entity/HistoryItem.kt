package com.ineedyourcode.domain.entity

data class HistoryItem(
    val ID: String,
    val word: String,
    var isFavorite: Boolean = false,
)