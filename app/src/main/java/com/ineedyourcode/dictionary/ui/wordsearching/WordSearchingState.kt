package com.ineedyourcode.dictionary.ui.wordsearching

import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

sealed class WordSearchingState {
    object Loading : WordSearchingState()
    class Success(val searchResult: List<SearchingResult>) : WordSearchingState()
    class Error(val error: ErrorMapper) : WordSearchingState()
}