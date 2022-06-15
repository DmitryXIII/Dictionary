package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.SearchingResult

interface WordSearchingUsecase {
    suspend fun search(word: String): List<SearchingResult>
}
