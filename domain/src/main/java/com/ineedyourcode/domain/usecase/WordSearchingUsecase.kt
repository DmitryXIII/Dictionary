package com.ineedyourcode.domain.usecase

import com.ineedyourcode.domain.entity.SearchingResultItem

interface WordSearchingUsecase : RemoteDatasourceUsecase {
    fun saveSearchingResultToHistory(searchingResultItem: SearchingResultItem)
}