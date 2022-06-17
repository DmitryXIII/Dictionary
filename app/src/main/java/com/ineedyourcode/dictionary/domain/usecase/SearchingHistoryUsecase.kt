package com.ineedyourcode.dictionary.domain.usecase

interface SearchingHistoryUsecase {
    fun <T> getSearchingHistory(): List<T>
    fun addToSearchingHistory(word: String)
}