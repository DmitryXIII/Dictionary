package com.ineedyourcode.dictionary.ui.wordtranslating

import android.content.Context
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.domain.ResponseCodes

class ErrorMapper {
    fun getErrorMessage(context: Context, error: String): String {
        return when (error) {
            ResponseCodes.INVALID_REQUEST.code -> {
                context.getString(R.string.error_300_message)
            }

            ResponseCodes.EMPTY_REQUEST.code -> {
                context.getString(R.string.error_301_message)
            }

            else -> {
                error
            }
        }
    }
}