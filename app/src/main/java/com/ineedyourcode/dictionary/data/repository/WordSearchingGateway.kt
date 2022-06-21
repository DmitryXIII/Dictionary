package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.data.datasource.local.RoomDataSource
import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity
import com.ineedyourcode.dictionary.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.dictionary.domain.usecase.GatewayUsecase

class WordSearchingGateway(
    val remoteDataSource: RetrofitDataSource,
    val localDataSource: RoomDataSource,
) : GatewayUsecase {
    @Suppress("UNCHECKED_CAST")
    override fun <T> getSearchingHistory(): List<T> {
        return localDataSource.getSearchingHistory<SearchingHistoryEntity>() as List<T>
    }

    override fun addToSearchingHistory(word: SearchingHistoryEntity) {
    }

//    override fun addToSearchingHistory(word: String) {
//        localDataSource.addToSearchingHistory(word)
//    }

    override fun addToFavorite(word: String) {
        localDataSource.addToFavorite(word)
    }

    override fun deleteFromFavorite (word: String) {
        localDataSource.deleteFromFavorite(word)
    }


    override suspend fun searchInDictionary(word: String) = remoteDataSource.searchInDictionary(word)
}