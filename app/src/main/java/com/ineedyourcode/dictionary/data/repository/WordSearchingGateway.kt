package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import io.reactivex.rxjava3.core.Single

class WordSearchingGateway(val dataSource: WordSearchingUsecase) : WordSearchingUsecase {
    override fun search(word: String): Single<List<SearchingResult>> {
        return dataSource.search(word)
    }
}