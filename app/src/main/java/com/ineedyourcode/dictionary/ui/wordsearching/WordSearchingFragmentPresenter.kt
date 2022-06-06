package com.ineedyourcode.dictionary.ui.wordsearching

import com.ineedyourcode.dictionary.data.repository.WordSearchingGateway
import com.ineedyourcode.dictionary.domain.entity.ResponseCodes
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class WordSearchingFragmentPresenter : WordSearchingFragmentPresenterContract {

    private val repository: WordSearchingUsecase = WordSearchingGateway()

    private var targetView: WordSearchingViewContract? = null

    override fun onAttach(fragment: WordSearchingViewContract) {
        targetView = fragment
    }

    override fun searchWord(word: String) {
        if (word.isNotEmpty()) {
            targetView?.showProgress()
            repository.search(word)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        targetView?.hideProgress()
                        targetView?.showTranslatingResult(it)
                    },
                    onError = {
                        targetView?.hideProgress()
                        when (it.message) {
                            ResponseCodes.INVALID_REQUEST.code -> {
                                targetView?.showTranslatingError(
                                    ErrorMapper.StringResource(ResponseCodes.INVALID_REQUEST))
                            }

                            else -> {
                                targetView?.showTranslatingError(
                                    ErrorMapper.DirectString(it.message.toString()))
                            }
                        }
                    }
                )
        } else {
            targetView?.showTranslatingError(ErrorMapper.StringResource(ResponseCodes.EMPTY_REQUEST))
        }
    }

    override fun onDetach() {
        targetView = null
    }
}