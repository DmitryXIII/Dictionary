package com.ineedyourcode.dictionary.data.datasource.local

import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity
import com.ineedyourcode.dictionary.data.datasource.local.entities.WordMeaningEntity
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning

class EntitiesMapper {
    fun convertSearchingResultItemToHistoryEntity(
        searchingResultItem: SearchingResultItem,
    ): HistoryEntity {
        return with(searchingResultItem) {
            HistoryEntity(ID, wordTranslation)
        }
    }

    fun convertSearchingResultItemToWordMeaningsEntityList(
        searchingResultItem: SearchingResultItem,
    ): List<WordMeaningEntity> {
        return searchingResultItem.wordMeanings.map {
            WordMeaningEntity(it.ID,
                searchingResultItem.ID,
                it.imageUrl,
                it.transcription,
                it.translation,
            it.note ?: "---")
        }
    }

    fun convertWordMeaningsEntityListToWordMeaningsList(
        wordMeaningEntityList: List<WordMeaningEntity>,
    ): List<WordMeaning> {
        return wordMeaningEntityList.map {
            WordMeaning(
                ID = it.id,
                imageUrl = it.imageUrl,
                translation = it.translation,
                transcription = it.transcription,
                note = it.note
            )
        }
    }
}
