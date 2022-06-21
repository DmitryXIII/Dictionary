package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase

class RetrofitDataSource(
    private val retrofit: SkyengApi,
    private val mapper: SearchingDtoMapper,
) : WordSearchingUsecase {

    override suspend fun searchInDictionary(word: String) =
        retrofit.searchAsync(word).await().map { mapper.convertResultDtoToEntity(it) }
}