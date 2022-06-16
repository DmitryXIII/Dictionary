package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.data.datasource.local.RoomDataSource
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase

class WordSearchingGateway(
    val remoteDataSource: WordSearchingUsecase,
    val localDataSource: RoomDataSource,
) : WordSearchingUsecase {
    override suspend fun search(word: String) = remoteDataSource.search(word)
}