package com.ineedyourcode.dictionary.data.datasource.remote.dto


import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("note")
    val note: Any?,
    @SerializedName("text")
    val text: String
)