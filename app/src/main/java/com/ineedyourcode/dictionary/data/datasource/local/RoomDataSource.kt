package com.ineedyourcode.dictionary.data.datasource.local

import com.ineedyourcode.dictionary.data.datasource.local.dao.HistoryDao
import com.ineedyourcode.dictionary.data.datasource.local.dao.WordMeaningDao
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning
import com.ineedyourcode.dictionary.domain.usecase.HistoryUsecase

class RoomDataSource(
    private val wordMeaningDao: WordMeaningDao,
    private val historyDao: HistoryDao,
    private val entityMapper: EntitiesMapper,
) :
    HistoryUsecase {
    @Suppress("UNCHECKED_CAST")
    override fun <T> getHistory(): List<T> {
        return historyDao.getSearchingHistory() as List<T>
    }

    override fun addToHistory(searchingResultItem: SearchingResultItem) {
        historyDao.addToHistory(entityMapper.convertSearchingResultItemToHistoryEntity(
            searchingResultItem))

        for (wordMeaningEntity in entityMapper.convertSearchingResultItemToWordMeaningsEntityList(
            searchingResultItem)) {
            wordMeaningDao.addMeaning(wordMeaningEntity)
        }
    }

    override fun addToFavorite(word: String) {
        historyDao.addToFavorite(word)
    }

    override fun deleteFromFavorite(word: String) {
        historyDao.deleteFromFavorite(word)
    }

    override fun getWordMeanings(wordId: String): List<WordMeaning> {
        return entityMapper.convertWordMeaningsEntityListToWordMeaningsList(wordMeaningDao.getMeaningByOwnerId(
            wordId))
    }
}