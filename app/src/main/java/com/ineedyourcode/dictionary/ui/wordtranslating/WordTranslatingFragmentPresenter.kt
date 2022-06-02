package com.ineedyourcode.dictionary.ui.wordtranslating

import com.ineedyourcode.dictionary.data.datasource.remote.RetrofitDataSource
import com.ineedyourcode.dictionary.domain.usecase.WordTranslateUsecase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy

class WordTranslatingFragmentPresenter : WordTranslatingFragmentPresenterContract {

    private val repository: WordTranslateUsecase = RetrofitDataSource()

    private var targetFragment: WordTranslatingViewContract? = null

    override fun onAttach(fragment: WordTranslatingViewContract) {
        targetFragment = fragment
    }

    override fun searchWord(word: String) {
        repository.translate(word)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { targetFragment?.showTranslatingResult(it) },
                onError = { targetFragment?.showTranslatingError(it.message.toString()) }
            )
    }

    override fun onDetach() {
        targetFragment = null
    }
}