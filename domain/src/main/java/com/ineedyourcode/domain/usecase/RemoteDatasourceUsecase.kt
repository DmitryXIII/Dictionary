package com.ineedyourcode.domain.usecase

import com.ineedyourcode.domain.entity.SearchingResultItem

interface RemoteDatasourceUsecase {
    suspend fun searchInDictionary(word: String): List<SearchingResultItem>
}