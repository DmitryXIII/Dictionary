package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.SearchingResultDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET ("words/search")
    fun search(
        @Query("search") searchingRequest: String
    ): Single<List<SearchingResultDto>>
}