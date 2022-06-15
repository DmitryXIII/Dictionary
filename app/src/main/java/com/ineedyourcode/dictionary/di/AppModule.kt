package com.ineedyourcode.dictionary.di

import com.ineedyourcode.dictionary.BuildConfig
import com.ineedyourcode.dictionary.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.dictionary.data.datasource.remote.SearchingDtoMapper
import com.ineedyourcode.dictionary.data.datasource.remote.SkyengApi
import com.ineedyourcode.dictionary.data.repository.WordSearchingGateway
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
private const val BUILD_CONFIG_TYPE_DEBUG = "debug"
const val NAME_GATEWAY = "Gateway"
const val NAME_DATASOURCE = "Datasource"

val appModule = module {
    viewModel {
        WordSearchingViewModel(gateway = get(named(NAME_GATEWAY)),
            savedStateHandle = get())
    }

    single<WordSearchingUsecase>(named(NAME_GATEWAY)) {
        WordSearchingGateway(dataSource = get(named(
            NAME_DATASOURCE)))
    }

    single<WordSearchingUsecase>(named(NAME_DATASOURCE)) {
        RetrofitDataSource(retrofit = get(),
            mapper = get())
    }

    single {
        val retrofit = Retrofit.Builder()

        if (BuildConfig.BUILD_TYPE == BUILD_CONFIG_TYPE_DEBUG) {
            retrofit.client(OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
        }

        retrofit.apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }

        retrofit.build().create(SkyengApi::class.java)
    }

    factory { SearchingDtoMapper() }
}