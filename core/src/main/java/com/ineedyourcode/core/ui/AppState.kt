package com.ineedyourcode.core.ui

import com.ineedyourcode.core.ui.uils.ErrorMapper

sealed class AppState {
    object Loading : AppState()
    data class Success(val result: Any) : AppState()
    data class Error(val error: ErrorMapper) : AppState()
}