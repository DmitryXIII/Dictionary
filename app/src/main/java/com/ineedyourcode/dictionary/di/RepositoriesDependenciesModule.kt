package com.ineedyourcode.dictionary.di

import com.ineedyourcode.dictionary.BuildConfig
import com.ineedyourcode.dictionary.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.dictionary.data.datasource.remote.SearchingDtoMapper
import com.ineedyourcode.dictionary.data.datasource.remote.SkyengApi
import com.ineedyourcode.dictionary.data.repository.WordSearchingGateway
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
private const val BUILD_CONFIG_TYPE_DEBUG = "debug"
const val DATASOURCE_NAME = "DataSource"
const val GATEWAY_NAME = "Gateway"

@Module
class RepositoriesDependenciesModule {
    @Singleton
    @Provides
    @Named(GATEWAY_NAME)
    fun provideGateway(@Named(DATASOURCE_NAME) dataSource: WordSearchingUsecase): WordSearchingUsecase {
        return WordSearchingGateway(dataSource)
    }

    @Singleton
    @Provides
    @Named(DATASOURCE_NAME)
    fun provideDataSource(retrofit: SkyengApi, mapper: SearchingDtoMapper): WordSearchingUsecase {
        return RetrofitDataSource(retrofit, mapper)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): SkyengApi {
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
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        }

        return retrofit.build().create(SkyengApi::class.java)
    }

    @Provides
    fun provideDtoMapper(): SearchingDtoMapper {
        return SearchingDtoMapper()
    }
}