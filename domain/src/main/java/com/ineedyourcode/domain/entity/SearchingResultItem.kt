package com.ineedyourcode.domain.entity

data class SearchingResultItem(
    val ID: String,
    val wordTranslation: String,
    val wordMeanings: List<WordMeaning>
)