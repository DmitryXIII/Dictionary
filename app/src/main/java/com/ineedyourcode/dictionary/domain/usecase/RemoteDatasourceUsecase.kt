package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem

interface RemoteDatasourceUsecase {
    suspend fun searchInDictionary(word: String): List<SearchingResultItem>
}