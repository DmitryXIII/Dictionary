package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning

interface DetailsUsecase: FavoriteUsecase {
    fun getWordMeanings(wordId: String): List<WordMeaning>
    fun getHistoryItem(word: String) : HistoryItem
}