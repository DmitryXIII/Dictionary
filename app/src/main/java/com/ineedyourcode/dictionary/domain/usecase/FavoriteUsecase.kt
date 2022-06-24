package com.ineedyourcode.dictionary.domain.usecase

import com.ineedyourcode.dictionary.domain.entity.HistoryItem

interface FavoriteUsecase {
    fun updateFavorite(historyItem: HistoryItem)
}