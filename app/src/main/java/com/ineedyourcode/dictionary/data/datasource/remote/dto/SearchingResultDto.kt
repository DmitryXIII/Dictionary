package com.ineedyourcode.dictionary.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class SearchingResultDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("meanings")
    val meanings: List<Meaning>,
    @SerializedName("text")
    val text: String
)