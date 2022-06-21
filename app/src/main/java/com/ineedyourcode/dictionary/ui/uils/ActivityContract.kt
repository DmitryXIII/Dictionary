package com.ineedyourcode.dictionary.ui.uils

import com.ineedyourcode.dictionary.domain.entity.SearchingResultItem

interface ActivityContract {
    fun checkInternet(): Boolean
    fun navigateToHistory()
    fun navigateToFavorite()
    fun openWordDetails(searchingResultItem: SearchingResultItem)
}