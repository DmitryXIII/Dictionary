package com.ineedyourcode.dictionary.ui.uils

import androidx.fragment.app.Fragment

interface ActivityContract {
    fun checkInternet() : Boolean
    fun navigateToHistory()
    fun navigateToFavorite()
}