package com.ineedyourcode.core.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchingResultItemDto(
    @SerializedName("id")
    val ID : String,
    @SerializedName("meanings")
    val meanings: List<MeaningDto>,
    @SerializedName("text")
    val text: String
)