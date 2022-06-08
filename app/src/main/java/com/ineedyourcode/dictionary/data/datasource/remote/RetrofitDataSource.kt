package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class RetrofitDataSource(
    private val retrofit: SkyengApi,
    private val mapper: SearchingDtoMapper,
) : WordSearchingUsecase {

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