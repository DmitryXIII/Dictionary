package com.ineedyourcode.dictionary.ui.uils

import com.ineedyourcode.domain.entity.HistoryItem
import com.ineedyourcode.domain.entity.SearchingResultItem

interface ActivityContract {
    fun checkInternet(): Boolean
    fun openHistory()
    fun openWordDetailsWithSavingToHistory(searchingResultItem: SearchingResultItem)
    fun openWordDetailsFromHistory(historyItem: HistoryItem)
}