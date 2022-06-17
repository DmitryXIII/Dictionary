package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.SearchingResult

interface WordSearchingUsecase {
    suspend fun searchInDictionary(word: String): List<SearchingResult>
}
