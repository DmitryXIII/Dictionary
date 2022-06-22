package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.data.datasource.local.RoomDataSource
import com.ineedyourcode.dictionary.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning
import com.ineedyourcode.dictionary.domain.usecase.GatewayUsecase

class WordGateway(
    val remoteDataSource: RetrofitDataSource,
    val localDataSource: RoomDataSource,
) : GatewayUsecase {
    override fun getHistory(): List<HistoryItem> {
        return localDataSource.getHistory()
    }

    override fun saveSearchingResultToHistory(searchingResultItem: SearchingResultItem) {
        addToHistory(searchingResultItem)
    }

    override fun addToFavorite(word: String) {
        localDataSource.addToFavorite(word)
    }

    override fun deleteFromFavorite(word: String) {
        localDataSource.deleteFromFavorite(word)
    }

    override fun addToHistory(searchingResultItem: SearchingResultItem) {
        localDataSource.addToHistory(searchingResultItem)
    }

    override fun getWordMeanings(wordId: String): List<WordMeaning> {
        return localDataSource.getWordMeanings(wordId)
    }

    override suspend fun searchInDictionary(word: String) =
        remoteDataSource.searchInDictionary(word)
}