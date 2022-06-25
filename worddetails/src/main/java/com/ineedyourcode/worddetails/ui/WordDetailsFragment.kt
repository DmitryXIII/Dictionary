package com.ineedyourcode.worddetails.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.core.ui.BaseFragment
import com.ineedyourcode.core.ui.uils.setFavoriteIcon
import com.ineedyourcode.core.ui.uils.showErrorSnack
import com.ineedyourcode.core.ui.uils.ErrorMapper
import com.ineedyourcode.domain.entity.HistoryItem
import com.ineedyourcode.domain.entity.WordMeaning
import com.ineedyourcode.worddetails.databinding.FragmentWordDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val WORD_ARG_KEY = "WORD_ARG_KEY"

class WordDetailsFragment :
    BaseFragment<FragmentWordDetailsBinding, List<WordMeaning>>(FragmentWordDetailsBinding::inflate) {

    private var currentHistoryItem: HistoryItem? = null
    private val detailsAdapter = WordDetailsAdapter()

    override val viewModel: WordDetailsViewModel by viewModel()

    companion object {
        fun newInstance(word: String): WordDetailsFragment {
            return WordDetailsFragment().apply {
                arguments = bundleOf(WORD_ARG_KEY to word)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            it.getString(WORD_ARG_KEY)?.let { word ->
                viewModel.getHistoryItem(word).observe(viewLifecycleOwner) { historyItem ->
                    currentHistoryItem = historyItem
                    binding.detailsFavoriteImageView.setFavoriteIcon(historyItem)
                }

                viewModel.getWordMeanings(word).observe(viewLifecycleOwner) { state ->
                    renderData(state)
                }
                binding.detailsWordTitleTextView.text = word
            }
        }

        binding.detailsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = detailsAdapter
        }

        binding.detailsFavoriteImageView.apply {
            setOnClickListener {
                currentHistoryItem?.let { historyItem ->
                    historyItem.isFavorite = !historyItem.isFavorite
                    viewModel.updateFavorite(historyItem)
                    binding.detailsFavoriteImageView.setFavoriteIcon(historyItem)
                }
            }
        }
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