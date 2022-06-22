package com.ineedyourcode.dictionary.data.repository

import com.ineedyourcode.dictionary.data.datasource.local.RoomDataSource
import com.ineedyourcode.dictionary.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning
import com.ineedyourcode.dictionary.domain.usecase.GatewayUsecase
import kotlinx.coroutines.flow.Flow

class WordGateway(
    val remoteDataSource: RetrofitDataSource,
    val localDataSource: RoomDataSource,
) : GatewayUsecase {
    override fun getHistory(query: String): Flow<List<HistoryItem>> {
        return localDataSource.getHistory(query)
    }

    override fun saveSearchingResultToHistory(searchingResultItem: SearchingResultItem) {
        addToHistory(searchingResultItem)
    }

    override fun updateFavorite(historyItem: HistoryItem) {
        localDataSource.updateFavorite(historyItem)
    }

    override fun addToHistory(searchingResultItem: SearchingResultItem) {
        localDataSource.addToHistory(searchingResultItem)
    }

    override fun getWordMeanings(wordId: String): List<WordMeaning> {
        return localDataSource.getWordMeanings(wordId)
    }

    override fun getHistoryItem(word: String): HistoryItem {
       return localDataSource.getHistoryItem(word)
    }

    override suspend fun searchInDictionary(word: String) =
        remoteDataSource.searchInDictionary(word)
}