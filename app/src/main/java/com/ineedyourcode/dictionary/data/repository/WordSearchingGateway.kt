package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.DomainCallback
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase

class WordSearchingGateway(val dataSource: WordSearchingUsecase) : WordSearchingUsecase {
    override fun search(word: String, callback: DomainCallback<List<SearchingResult>>) {
        return dataSource.search(word, callback)
    }
}