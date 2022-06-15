package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingCallback
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase

class WordSearchingGateway(val dataSource: WordSearchingUsecase) : WordSearchingUsecase {
    override fun search(word: String, callback: WordSearchingCallback<List<SearchingResult>>) {
        return dataSource.search(word, callback)
    }
}