package com.ineedyourcode.dictionary.ui.uils

import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity

interface ActivityContract {
    fun checkInternet(): Boolean
    fun navigateToHistory()
    fun navigateToFavorite()
    fun openWordDetails(searchingHistoryEntity: SearchingHistoryEntity)
}