package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem

interface WordSearchingUsecase {
    suspend fun searchInDictionary(word: String): List<SearchingResultItem>
}
