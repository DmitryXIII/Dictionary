package com.ineedyourcode.dictionary.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordMeaning(
    val ID : Int,
    val imageUrl: String,
    val transcription: String,
    val translation: String,
    val note: String?
) : Parcelable