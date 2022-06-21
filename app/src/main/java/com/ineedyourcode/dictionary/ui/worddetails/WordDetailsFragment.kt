package com.ineedyourcode.dictionary.ui.worddetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity
import com.ineedyourcode.dictionary.databinding.FragmentWordDetailsBinding
import com.ineedyourcode.dictionary.ui.BaseFragment
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

private const val WORD_ARG_KEY = "WORD_ARG_KEY"

class WordDetailsFragment :
    BaseFragment<FragmentWordDetailsBinding, SearchingHistoryEntity>(FragmentWordDetailsBinding::inflate) {

    private var currentWord: SearchingHistoryEntity? = null

    companion object {
        fun newInstance(word: SearchingHistoryEntity): WordDetailsFragment {
            return WordDetailsFragment().apply {
                arguments = bundleOf(WORD_ARG_KEY to word)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            currentWord = it.getParcelable(WORD_ARG_KEY)
        }

        binding.detailsWaordTitleTextView.text = currentWord?.word
    }



    override val viewModel: ViewModelContract.BaseViewModel by viewModels()


    override fun showResult(result: SearchingHistoryEntity) {

    }

    override fun showError(error: ErrorMapper) {

    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }
}