package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.data.datasource.local.RoomDataSource
import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity
import com.ineedyourcode.dictionary.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning
import com.ineedyourcode.dictionary.domain.usecase.GatewayUsecase

class WordGateway(
    val remoteDataSource: RetrofitDataSource,
    val localDataSource: RoomDataSource,
) : GatewayUsecase {
    @Suppress("UNCHECKED_CAST")
    override fun <T> getHistory(): List<T> {
        return localDataSource.getHistory<HistoryEntity>() as List<T>
    }

    override fun addToHistory(searchingResultItem: SearchingResultItem) {
        localDataSource.addToHistory(searchingResultItem)
    }


//    override fun addToSearchingHistory(word: String) {
//        localDataSource.addToSearchingHistory(word)
//    }

    override fun addToFavorite(word: String) {
        localDataSource.addToFavorite(word)
    }

    override fun deleteFromFavorite(word: String) {
        localDataSource.deleteFromFavorite(word)
    }

    override fun getWordMeanings(wordId: String): List<WordMeaning> {
        return localDataSource.getWordMeanings(wordId)
    }

    override suspend fun searchInDictionary(word: String) =
        remoteDataSource.searchInDictionary(word)
}