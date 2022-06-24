package com.ineedyourcode.dictionary.domain.entity

data class SearchingResultItem(
    val ID: String,
    val wordTranslation: String,
    val wordMeanings: List<WordMeaning>
)