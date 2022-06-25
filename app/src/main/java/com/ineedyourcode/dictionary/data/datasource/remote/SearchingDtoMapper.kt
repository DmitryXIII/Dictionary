package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.MeaningDto
import com.ineedyourcode.dictionary.data.datasource.remote.dto.SearchingResultItemDto
import com.ineedyourcode.domain.entity.SearchingResultItem
import com.ineedyourcode.domain.entity.WordMeaning

class SearchingDtoMapper {
    fun convertResultDtoToEntity(result: SearchingResultItemDto): SearchingResultItem =
        SearchingResultItem(
            result.ID,
            result.text,
            result.meanings.map { convertMeaningDtoToWordMeaning(it) })

    private fun convertMeaningDtoToWordMeaning(meaningDto: MeaningDto): WordMeaning {
        with(meaningDto) {
            return WordMeaning(ID,
                imageUrl,
                transcription,
                translation.text,
                translation.note ?: "---")
        }
    }
}