package com.ineedyourcode.core.data.datasource.remote

import com.ineedyourcode.core.data.datasource.remote.dto.SearchingResultItemDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET ("words/search")
    fun searchAsync(
        @Query("search") searchingRequest: String
    ): Deferred<List<SearchingResultItemDto>>
}