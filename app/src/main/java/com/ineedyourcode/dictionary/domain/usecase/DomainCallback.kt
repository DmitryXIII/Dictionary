package com.ineedyourcode.dictionary.domain.usecase

interface DomainCallback<T> {
    fun onSuccess(result: T)
    fun onError(error: String)
}