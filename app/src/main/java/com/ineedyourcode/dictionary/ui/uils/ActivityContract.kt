package com.ineedyourcode.dictionary.ui.uils

import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity
import com.ineedyourcode.dictionary.domain.entity.SearchingResult

interface ActivityContract {
    fun checkInternet(): Boolean
    fun navigateToHistory()
    fun navigateToFavorite()
    fun openWordDetails(searchingResult: SearchingResult)
}