package com.ineedyourcode.dictionary.di

import com.ineedyourcode.core.data.datasource.local.DictionaryDatabase
import com.ineedyourcode.core.data.datasource.local.EntitiesMapper
import com.ineedyourcode.core.data.datasource.local.RoomDataSource
import com.ineedyourcode.core.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.core.data.datasource.remote.SearchingDtoMapper
import com.ineedyourcode.core.data.datasource.remote.SkyengApi
import com.ineedyourcode.core.data.repository.WordGateway
import com.ineedyourcode.dictionary.BuildConfig
import com.ineedyourcode.domain.usecase.DetailsUsecase
import com.ineedyourcode.domain.usecase.GatewayUsecase
import com.ineedyourcode.domain.usecase.HistoryUsecase
import com.ineedyourcode.domain.usecase.WordSearchingUsecase
import com.ineedyourcode.history.ui.SearchingHistoryFragment
import com.ineedyourcode.history.ui.SearchingHistoryViewModel
import com.ineedyourcode.worddetails.ui.WordDetailsFragment
import com.ineedyourcode.worddetails.ui.WordDetailsViewModel
import com.ineedyourcode.wordsearching.ui.WordSearchingViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
private const val BUILD_CONFIG_TYPE_DEBUG = "debug"

val historyModule = module {
    scope<SearchingHistoryFragment> {
        viewModel {
            SearchingHistoryViewModel(gateway = get())
        }

        scoped<HistoryUsecase> {
            WordGateway(remoteDataSource = get(), localDataSource = get())
        }
    }
}

val searchingModule = module {
    viewModel {
        WordSearchingViewModel(gateway = get(),
            savedStateHandle = get())
    }

    single<WordSearchingUsecase> {
        WordGateway(remoteDataSource = get(), localDataSource = get())
    }
}


val detailsModule = module {
    scope<WordDetailsFragment> {
        viewModel {
            WordDetailsViewModel(gateway = get())
        }
        scoped<DetailsUsecase> {
            WordGateway(remoteDataSource = get(), localDataSource = get())
        }
    }
}

val datasourceModule = module {
    single<GatewayUsecase> {
        WordGateway(remoteDataSource = get(), localDataSource = get())
    }

    single {
        RetrofitDataSource(retrofit = get(),
            mapper = get())
    }

    single { RoomDataSource(wordMeaningDao = get(), historyDao = get(), entityMapper = get()) }
}

val retrofitModule = module {
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
            addCallAdapterFactory(CoroutineCallAdapterFactory())
        }

        retrofit.build().create(SkyengApi::class.java)
    }

    factory { SearchingDtoMapper() }
}

val roomModule = module {
    single { DictionaryDatabase.getUserDatabase(androidContext()) }

    single { get<DictionaryDatabase>().wordMeaningDao }
    single { get<DictionaryDatabase>().historyDao }

    factory { EntitiesMapper() }
}