package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase

class WordSearchingGateway(val dataSource: WordSearchingUsecase) : WordSearchingUsecase {
    override suspend fun search(word: String) = dataSource.search(word)
}