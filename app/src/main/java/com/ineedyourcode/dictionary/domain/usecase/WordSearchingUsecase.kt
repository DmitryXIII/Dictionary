package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem

interface WordSearchingUsecase : RemoteDatasourceUsecase {
    fun saveSearchingResultToHistory(searchingResultItem: SearchingResultItem)
}
