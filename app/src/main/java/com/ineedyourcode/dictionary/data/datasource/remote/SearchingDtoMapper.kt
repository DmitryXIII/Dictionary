package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.MeaningDto
import com.ineedyourcode.dictionary.data.datasource.remote.dto.SearchingResultDto
import com.ineedyourcode.dictionary.domain.entity.SearchingResult

class SearchingDtoMapper {
    fun convertSearchingResultDtoListToEntityList(
        result: List<SearchingResultDto>,
    ): List<SearchingResult> {
        return result.map {
            SearchingResult(it.text, it.meanings.first().translation.text)
        }
    }
}