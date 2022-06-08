package com.ineedyourcode.dictionary.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName



data class MeaningDto(
    @SerializedName("id")
    val ID : Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String,
    @SerializedName("previewUrl")
    val previewUrl: String,
    @SerializedName("soundUrl")
    val soundUrl: String,
    @SerializedName("transcription")
    val transcription: String,
    @SerializedName("translation")
    val translation: TranslationDto,
)