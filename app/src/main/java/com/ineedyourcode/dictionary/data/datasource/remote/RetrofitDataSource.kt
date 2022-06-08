package com.ineedyourcode.dictionary.data.datasource.remote

import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.DomainCallback
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RetrofitDataSource(
    private val retrofit: SkyengApi,
    private val mapper: SearchingDtoMapper,
) : WordSearchingUsecase {

    override fun search(word: String, callback: DomainCallback<List<SearchingResult>>) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = retrofit.search(word)
            if (result.isSuccessful) {
                result.body()?.let {
                    if (it.isNotEmpty()) {
                        callback.onSuccess(mapper.convertSearchingResultDtoListToEntityList(it))
                    } else {
                        callback.onError(ResponseCodes.INVALID_REQUEST.code)
                    }
                }
            } else {
                callback.onError(result.message())
            }
        }
    }
}