package com.ineedyourcode.dictionary.domain.usecase

interface WordSearchingCallback<T> {
    fun onSuccess(result: T)
    fun onError(error: String)
}