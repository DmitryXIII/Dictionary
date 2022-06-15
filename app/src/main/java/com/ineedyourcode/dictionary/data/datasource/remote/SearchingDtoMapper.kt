package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.SearchingResultDto
import com.ineedyourcode.dictionary.domain.entity.SearchingResult

class SearchingDtoMapper {
    fun convertResultDtoListToEntity(result: SearchingResultDto): SearchingResult =
        SearchingResult(result.text, result.meanings.first().translation.text)
}