package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.domain.usecase.RemoteDatasourceUsecase

class RetrofitDataSource(
    private val retrofit: SkyengApi,
    private val mapper: SearchingDtoMapper,
) : RemoteDatasourceUsecase {

    override suspend fun searchInDictionary(word: String) =
        retrofit.searchAsync(word).await().map { mapper.convertResultDtoToEntity(it) }
}