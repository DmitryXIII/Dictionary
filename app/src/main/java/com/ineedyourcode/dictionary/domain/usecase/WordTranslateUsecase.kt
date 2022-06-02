package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.TranslatingResult
import io.reactivex.rxjava3.core.Single

interface WordTranslateUsecase {
    fun translate(word: String) : Single<TranslatingResult>
}
