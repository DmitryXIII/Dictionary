package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning

interface HistoryUsecase {
    fun <T> getHistory(): List<T>
    fun addToHistory(searchingResultItem: SearchingResultItem)
    fun addToFavorite(word: String)
    fun deleteFromFavorite(word: String)
    fun getWordMeanings(wordId: String): List<WordMeaning>
}