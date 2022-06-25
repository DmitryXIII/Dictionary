package com.ineedyourcode.dictionary.ui.uils

interface ActivityContract {
    fun checkInternet(): Boolean
    fun openHistory()
    fun openWordDetailsWithSavingToHistory(wordTranslation: String)
    fun openWordDetailsFromHistory(word: String)
}