package com.ineedyourcode.dictionary.ui.wordsearching

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.databinding.FragmentWordSearchingBinding
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import com.ineedyourcode.dictionary.ui.uils.hideKeyboard
import com.ineedyourcode.dictionary.ui.uils.showErrorSnack

const val ANIMATION_DURATION = 100L
const val ANIMATION_ALPHA_VISIBLE = 1f
const val ANIMATION_ALPHA_INVISIBLE = 0f

class WordSearchingFragment : Fragment(R.layout.fragment_word_searching),
    WordSearchingViewContract {

    private var _binding: FragmentWordSearchingBinding? = null
    private val binding: FragmentWordSearchingBinding
        get() = _binding!!

    private val wordTranslateAdapter = WordSearchingFragmentRecyclerViewAdapter()

    private val presenter: WordSearchingFragmentPresenterContract =
        WordSearchingFragmentPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWordSearchingBinding.bind(view)

        presenter.onAttach(this)

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
    }

    private fun searchWord() {
        hideKeyboard()
        presenter.searchWord(binding.wordTranslateEditText.text.toString())
    }

    override fun showTranslatingResult(result: List<SearchingResult>) {
        wordTranslateAdapter.setData(result)
        binding.wordTranslateRecyclerView.adapter = wordTranslateAdapter
    }

    override fun showTranslatingError(error: ErrorMapper) {
        wordTranslateAdapter.clearData()
        binding.wordTranslateRecyclerView.adapter = wordTranslateAdapter
        binding.wordTranslateRecyclerView.isVisible = false
        binding.lottie.animate()
            .alpha(ANIMATION_ALPHA_VISIBLE)
            .duration = ANIMATION_DURATION
        binding.root.showErrorSnack(error)
    }

    override fun showProgress() {
        binding.lottie.animate()
            .alpha(ANIMATION_ALPHA_INVISIBLE)
            .duration = ANIMATION_DURATION
        binding.wordTranslateRecyclerView.isVisible = false
        binding.translateWordProgressBar.isVisible = true
    }

    override fun hideProgress() {
        binding.wordTranslateRecyclerView.isVisible = true
        binding.translateWordProgressBar.isVisible = false
    }

    override fun hideKeyboard() {
        binding.root.hideKeyboard()
    }

    override fun onDestroyView() {
        binding.wordTranslateRecyclerView.adapter = null
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}