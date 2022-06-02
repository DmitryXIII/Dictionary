package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.data.datasource.remote.dto.TranslationResultDto
import com.ineedyourcode.dictionary.domain.entity.TranslationResult
import com.ineedyourcode.dictionary.domain.usecase.WordTranslateUsecase
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

class RetrofitDataSource : WordTranslateUsecase {

    private val mapper = TranslationDtoMapper()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(createClient())
            .build()
            .create(SkyengApi::class.java)
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    override fun translate(word: String): Single<List<TranslationResult>> {
        return retrofit.translate(word).map { mapper.convertTranslationResultDtoListToEntity(it) }
    }
}