package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.SearchingResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET ("words/search")
    suspend fun search(
        @Query("search") searchingRequest: String
    ): Response<List<SearchingResultDto>>
}