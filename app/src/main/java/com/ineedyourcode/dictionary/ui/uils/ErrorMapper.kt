package com.ineedyourcode.dictionary.ui.uils

import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.domain.entity.ResponseCodes

@Suppress("UNCHECKED_CAST")
sealed class ErrorMapper {

    class DirectString(private val errorMessage: String) : ErrorMapper(), ErrorMapperMessage {
        override fun <T> getMessage(): T {
            return errorMessage as T
        }
    }

    class StringResource(
        private val errorMessage: ResponseCodes,
    ) : ErrorMapper(), ErrorMapperMessage {
        override fun <T> getMessage(): T {
            return when (errorMessage) {
                ResponseCodes.INVALID_REQUEST -> {
                    R.string.error_300_message as T
                }
                ResponseCodes.EMPTY_REQUEST -> {
                    R.string.error_301_message as T
                }
            }
        }
    }
}

interface ErrorMapperMessage {
    fun <T> getMessage(): T
}