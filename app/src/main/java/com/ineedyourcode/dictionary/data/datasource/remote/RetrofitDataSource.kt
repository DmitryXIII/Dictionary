package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

class RetrofitDataSource : WordSearchingUsecase {

    private val mapper = SearchingDtoMapper()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
            .build()
            .create(SkyengApi::class.java)
    }

    override fun search(word: String): Single<List<SearchingResult>> {
        return Single.create { emitter ->
            retrofit.search(word).subscribeBy(
                onSuccess = {
                    if (it.isNotEmpty()) {
                        emitter.onSuccess(mapper.convertSearchingResultDtoListToEntityList(it))
                    } else {
                        emitter.onError(
                            NullPointerException(ResponseCodes.INVALID_REQUEST.code))
                    }
                },
                onError = { emitter.onError(it) }
            )
        }
    }
}