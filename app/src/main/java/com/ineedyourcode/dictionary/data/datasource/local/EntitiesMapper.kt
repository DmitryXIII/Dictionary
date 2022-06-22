package com.ineedyourcode.dictionary.data.datasource.local

import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity
import com.ineedyourcode.dictionary.data.datasource.local.entities.WordMeaningEntity
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem
import com.ineedyourcode.dictionary.domain.entity.WordMeaning

class EntitiesMapper {
    fun convertSearchingResultItemToHistoryEntity(
        searchingResultItem: SearchingResultItem,
    ): HistoryEntity {
        return with(searchingResultItem) {
            HistoryEntity(
                id = ID,
                word = wordTranslation
            )
        }
    }

    fun convertSearchingResultItemToWordMeaningsEntityList(
        searchingResultItem: SearchingResultItem,
    ): List<WordMeaningEntity> {
        return searchingResultItem.wordMeanings.map {
            WordMeaningEntity(
                ID = it.ID,
                meaningOwnerWord = searchingResultItem.wordTranslation,
                imageUrl = it.imageUrl,
                transcription = it.transcription,
                translation = it.translation,
                note = if (it.note.isNullOrBlank()) {
                    "---"
                } else {
                    it.note
                }
            )
        }
    }

    fun convertWordMeaningsEntityListToWordMeaningsList(
        wordMeaningEntityList: List<WordMeaningEntity>,
    ): List<WordMeaning> {
        return wordMeaningEntityList.map {
            WordMeaning(
                ID = it.ID,
                imageUrl = it.imageUrl,
                translation = it.translation,
                transcription = it.transcription,
                note = it.note
            )
        }
    }

    fun convertHistoryEntityToHistoryItem(historyEntity: HistoryEntity): HistoryItem {
        return with(historyEntity) {
            HistoryItem(
                ID = id,
                word = word,
                isFavorite = isFavorite
            )
        }
    }
}