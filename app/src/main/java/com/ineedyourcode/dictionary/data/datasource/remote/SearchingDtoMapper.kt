package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.MeaningDto
import com.ineedyourcode.dictionary.data.datasource.remote.dto.SearchingResultDto
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.entity.WordMeaning

class SearchingDtoMapper {
    fun convertResultDtoToEntity(result: SearchingResultDto): SearchingResult =
        SearchingResult(result.text, result.meanings.map { convertMeaningDtoToWordMeaning(it) })

    private fun convertMeaningDtoToWordMeaning(meaningDto: MeaningDto): WordMeaning {
        with(meaningDto) {
            return WordMeaning(ID, imageUrl, transcription, translation.text, translation.note)
        }
    }
}