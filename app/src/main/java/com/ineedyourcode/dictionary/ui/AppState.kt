package com.ineedyourcode.dictionary.ui

import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

sealed class AppState {
    object Loading : AppState()
    class Success(val result: Any) : AppState()
    data class Error(val error: ErrorMapper) : AppState()
}