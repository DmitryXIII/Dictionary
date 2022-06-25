package com.ineedyourcode.core.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class TranslationDto(
    @SerializedName("note")
    val note: String?,
    @SerializedName("text")
    val text: String
)