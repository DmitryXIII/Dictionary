package com.ineedyourcode.domain.usecase

import com.ineedyourcode.domain.entity.HistoryItem

interface FavoriteUsecase {
    fun updateFavorite(historyItem: HistoryItem)
}