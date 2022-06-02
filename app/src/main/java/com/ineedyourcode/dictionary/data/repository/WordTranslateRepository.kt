package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.dictionary.domain.entity.TranslationResult
import com.ineedyourcode.dictionary.domain.usecase.WordTranslateUsecase
import io.reactivex.rxjava3.core.Single

class WordTranslateRepository: WordTranslateUsecase {
    private val dataSource: WordTranslateUsecase = RetrofitDataSource()

    override fun translate(word: String): Single<List<TranslationResult>> {
        return dataSource.translate(word)
    }
}