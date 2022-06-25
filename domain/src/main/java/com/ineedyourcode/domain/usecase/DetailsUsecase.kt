package com.ineedyourcode.domain.usecase

import com.ineedyourcode.domain.entity.HistoryItem
import com.ineedyourcode.domain.entity.WordMeaning

interface DetailsUsecase: FavoriteUsecase {
    fun getWordMeanings(wordId: String): List<WordMeaning>
    fun getHistoryItem(word: String) : HistoryItem
}