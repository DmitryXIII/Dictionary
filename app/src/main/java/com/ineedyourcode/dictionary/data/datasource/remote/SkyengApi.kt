package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.SearchingResultItemDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET ("words/search")
    fun searchAsync(
        @Query("search") searchingRequest: String
    ): Deferred<List<SearchingResultItemDto>>
}