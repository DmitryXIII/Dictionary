package com.ineedyourcode.dictionary.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchingResultDto(
    @SerializedName("id")
    val ID : Int,
    @SerializedName("meanings")
    val meanings: List<MeaningDto>,
    @SerializedName("text")
    val text: String
)