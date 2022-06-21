package com.ineedyourcode.dictionary.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchingResult(
    val wordTranslation: String,
    val wordMeanings: List<WordMeaning>
) : Parcelable