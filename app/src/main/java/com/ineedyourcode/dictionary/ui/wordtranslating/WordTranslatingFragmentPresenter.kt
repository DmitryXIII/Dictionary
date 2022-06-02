package com.ineedyourcode.dictionary.ui.wordtranslating

import com.ineedyourcode.dictionary.data.repository.WordTranslateRepository
import com.ineedyourcode.dictionary.domain.ResponseCodes
import com.ineedyourcode.dictionary.domain.usecase.WordTranslateUsecase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class WordTranslatingFragmentPresenter : WordTranslatingFragmentPresenterContract {

    private val repository: WordTranslateUsecase = WordTranslateRepository()

    private var targetView: WordTranslatingViewContract? = null

    override fun onAttach(fragment: WordTranslatingViewContract) {
        targetView = fragment
    }

    override fun searchWord(word: String) {
        if (word.isNotEmpty()) {
            targetView?.showProgress()
            repository.translate(word)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        targetView?.hideProgress()
                        targetView?.showTranslatingResult(it)
                    },
                    onError = {
                        targetView?.hideProgress()
                        targetView?.showTranslatingError(it.message.toString())
                    }
                )
        } else {
            targetView?.showTranslatingError(ResponseCodes.EMPTY_REQUEST.code)
        }
    }

    override fun onDetach() {
        targetView = null
    }
}