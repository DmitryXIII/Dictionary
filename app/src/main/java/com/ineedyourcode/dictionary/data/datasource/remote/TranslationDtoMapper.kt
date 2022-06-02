package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.TranslationResultDto
import com.ineedyourcode.dictionary.domain.entity.TranslationResult

class TranslationDtoMapper {
    fun convertTranslationResultDtoListToEntity(
        result: List<TranslationResultDto>,
    ): List<TranslationResult> {
        return result.map {
            TranslationResult(it.text, it.meanings.first().translation.text)
        }
    }
}