package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.TranslationResultDto
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengApi {
    @GET ("words/search")
    fun translate(
        @Query("search") wordToSearch: String
    ): Single<List<TranslationResultDto>>
}