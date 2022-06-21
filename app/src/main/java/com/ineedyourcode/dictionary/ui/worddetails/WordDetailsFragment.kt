package com.ineedyourcode.dictionary.ui.worddetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.dictionary.databinding.FragmentWordDetailsBinding
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.ui.BaseFragment
import com.ineedyourcode.dictionary.ui.ViewModelContract
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper

private const val WORD_ARG_KEY = "WORD_ARG_KEY"

class WordDetailsFragment :
    BaseFragment<FragmentWordDetailsBinding, SearchingResult>(FragmentWordDetailsBinding::inflate) {

    private var currentWord: SearchingResult? = null
    private val detailsAdapter = WordDetailsAdapter()

    companion object {
        fun newInstance(searchingResult: SearchingResult): WordDetailsFragment {
            return WordDetailsFragment().apply {
                arguments = bundleOf(WORD_ARG_KEY to searchingResult)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            currentWord = it.getParcelable(WORD_ARG_KEY)
        }

        binding.detailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = detailsAdapter
        }

        binding.detailsWordTitleTextView.text = currentWord?.wordTranslation

        currentWord?.wordMeanings?.let {
            detailsAdapter.setData(it)
        }
    }

    override val viewModel: ViewModelContract.BaseViewModel by viewModels()

    override fun showResult(result: SearchingResult) {}

    override fun showError(error: ErrorMapper) {}

    override fun showProgress() {}

    override fun hideProgress() {}
}