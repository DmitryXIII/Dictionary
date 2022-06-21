package com.ineedyourcode.dictionary.ui.worddetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.dictionary.databinding.FragmentWordDetailsBinding
import com.ineedyourcode.dictionary.domain.entity.WordMeaning
import com.ineedyourcode.dictionary.ui.BaseFragment
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import com.ineedyourcode.dictionary.ui.uils.showErrorSnack
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val WORD_ARG_KEY = "WORD_ARG_KEY"
private const val WORD_ID_ARG_KEY = "WORD_ID_ARG_KEY"

class WordDetailsFragment :
    BaseFragment<FragmentWordDetailsBinding, List<WordMeaning>>(FragmentWordDetailsBinding::inflate) {

    private var currentWord: String? = null
    private var currentWordId: String? = null
    private val detailsAdapter = WordDetailsAdapter()

    override val viewModel: WordDetailsViewModel by viewModel()

    companion object {
        fun newInstance(word: String, wordId: String): WordDetailsFragment {
            return WordDetailsFragment().apply {
                arguments = bundleOf(WORD_ARG_KEY to word, WORD_ID_ARG_KEY to wordId)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            currentWord = it.getString(WORD_ARG_KEY)
            currentWordId = it.getString(WORD_ID_ARG_KEY)
        }

        binding.detailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = detailsAdapter
        }

        currentWordId?.let {
            viewModel.getWordMeanings(it).observe(viewLifecycleOwner) { state ->
                renderData(state)
            }
        }

        binding.detailsWordTitleTextView.text = currentWord

//        currentWord?.wordMeanings?.let {
//            detailsAdapter.setData(it)
//        }
    }


    override fun showResult(result: List<WordMeaning>) {
        detailsAdapter.setData(result)
    }

    override fun showError(error: ErrorMapper) {
        binding.root.showErrorSnack(error)
    }

    override fun showProgress() {
        binding.detailsProgressBar.isVisible = true
        binding.detailsRecyclerView.isVisible = false
    }

    override fun hideProgress() {
        binding.detailsProgressBar.isVisible = false
        binding.detailsRecyclerView.isVisible = true
    }
}