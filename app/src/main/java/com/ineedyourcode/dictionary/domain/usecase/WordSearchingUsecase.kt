package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import io.reactivex.rxjava3.core.Single

interface WordSearchingUsecase {
    fun search(word: String) : Single<List<SearchingResult>>
}
