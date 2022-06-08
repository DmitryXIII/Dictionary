package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.SearchingResult

interface WordSearchingUsecase {
    fun search(word: String, callback: DomainCallback<List<SearchingResult>>)
}
