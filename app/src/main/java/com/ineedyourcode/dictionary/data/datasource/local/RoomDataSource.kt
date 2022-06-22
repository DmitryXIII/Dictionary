package com.ineedyourcode.dictionary.data.datasource.local

import com.ineedyourcode.dictionary.data.datasource.local.dao.HistoryDao
import com.ineedyourcode.dictionary.data.datasource.local.dao.WordMeaningDao
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning
import com.ineedyourcode.dictionary.domain.usecase.LocalDatasourceUsecase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDataSource(
    private val wordMeaningDao: WordMeaningDao,
    private val historyDao: HistoryDao,
    private val entityMapper: EntitiesMapper,
) : LocalDatasourceUsecase {
    override fun getHistory(query: String): Flow<List<HistoryItem>> {
        return historyDao.getHistory(query)
            .map { entityMapper.convertHistoryEntityListToHistoryItemList(it) }
    }

    override fun addToHistory(searchingResultItem: SearchingResultItem) {
        historyDao.addToHistory(entityMapper.convertSearchingResultItemToHistoryEntity(
            searchingResultItem))

        for (wordMeaningEntity in entityMapper.convertSearchingResultItemToWordMeaningsEntityList(
            searchingResultItem)) {
            wordMeaningDao.addMeaning(wordMeaningEntity)
        }
    }

    override fun updateFavorite(historyItem: HistoryItem) {
        historyDao.updateFavorite(entityMapper.convertHistoryItemToHistoryEntity(historyItem))
    }

    override fun getWordMeanings(wordId: String): List<WordMeaning> {
        return entityMapper.convertWordMeaningsEntityListToWordMeaningsList(wordMeaningDao.getMeaningByOwnerId(
            wordId))
    }

    override fun getHistoryItem(word: String): HistoryItem {
        return entityMapper.convertHistoryEntityToHistoryItem(historyDao.getHistoryItem(word))
    }
}