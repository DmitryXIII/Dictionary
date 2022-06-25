package com.ineedyourcode.wordsearching.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.core.ui.BaseFragment
import com.ineedyourcode.core.ui.uils.ErrorMapper
import com.ineedyourcode.core.ui.uils.showErrorSnack
import com.ineedyourcode.domain.entity.SearchingResultItem
import com.ineedyourcode.wordsearching.databinding.FragmentWordSearchingBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel

private const val ANIMATION_DURATION = 100L
private const val ANIMATION_ALPHA_VISIBLE = 1f
private const val ANIMATION_ALPHA_INVISIBLE = 0f

class WordSearchingFragment :
    BaseFragment<FragmentWordSearchingBinding, List<SearchingResultItem>>(FragmentWordSearchingBinding::inflate) {

    override val viewModel: WordSearchingViewModel by stateViewModel()

    private val wordTranslateAdapter = WordSearchingFragmentRecyclerViewAdapter {
        viewModel.saveSearchingResultToHistory(it)
        mainController.openWordDetailsWithSavingToHistory(it.wordTranslation)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lottieState.observe(viewLifecycleOwner) {
            binding.lottie.alpha = it
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.wordTranslateRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.wordTranslateInputLayout.setEndIconOnClickListener {
            searchWord()
        }

        binding.wordTranslateEditText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    searchWord()
                }
            }
            false
        }

        binding.historyFab.setOnClickListener {
            mainController.openHistory()
        }
    }

    private fun searchWord() {
        ifConnectedToInternet {
            viewModel.searchWord(binding.wordTranslateEditText.text.toString())
        }
    }

    override fun showResult(result: List<SearchingResultItem>) {
        wordTranslateAdapter.setData(result)
        binding.wordTranslateRecyclerView.adapter = wordTranslateAdapter
    }

    override fun showError(error: ErrorMapper) {
        wordTranslateAdapter.clearData()
        binding.wordTranslateRecyclerView.adapter = wordTranslateAdapter
        binding.wordTranslateRecyclerView.isVisible = false
        animateLottie(ANIMATION_ALPHA_VISIBLE)
        binding.root.showErrorSnack(error)
    }

    override fun showProgress() {
        animateLottie(ANIMATION_ALPHA_INVISIBLE)
        binding.wordTranslateRecyclerView.isVisible = false
        binding.translateWordProgressBar.isVisible = true
    }

    override fun hideProgress() {
        binding.wordTranslateRecyclerView.isVisible = true
        binding.translateWordProgressBar.isVisible = false
    }

    override fun onDestroyView() {
        viewModel.saveLottieVisibilityState(binding.lottie.alpha)
        binding.wordTranslateRecyclerView.adapter = null
        super.onDestroyView()
    }

    private fun animateLottie(alpha: Float) {
        binding.lottie.animate()
            .alpha(alpha)
            .duration = ANIMATION_DURATION
    }
}