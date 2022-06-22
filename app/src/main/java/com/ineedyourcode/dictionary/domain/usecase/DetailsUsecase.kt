package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.WordMeaning

interface DetailsUsecase {
    fun getWordMeanings(wordId: String): List<WordMeaning>
}