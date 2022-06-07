package com.ineedyourcode.dictionary.ui.wordsearching

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.dictionary.app
import com.ineedyourcode.dictionary.databinding.FragmentWordSearchingBinding
import com.ineedyourcode.dictionary.di.GATEWAY_NAME
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.domain.usecase.WordSearchingUsecase
import com.ineedyourcode.dictionary.ui.BaseFragment
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import com.ineedyourcode.dictionary.ui.uils.NoConnectionDialogFragment
import com.ineedyourcode.dictionary.ui.uils.hideKeyboard
import com.ineedyourcode.dictionary.ui.uils.showErrorSnack
import javax.inject.Inject
import javax.inject.Named

const val ANIMATION_DURATION = 100L
const val ANIMATION_ALPHA_VISIBLE = 1f
const val ANIMATION_ALPHA_INVISIBLE = 0f

class WordSearchingFragment :
    BaseFragment<FragmentWordSearchingBinding>(FragmentWordSearchingBinding::inflate) {

    private val wordTranslateAdapter = WordSearchingFragmentRecyclerViewAdapter()

    @Inject
    @Named(GATEWAY_NAME)
    lateinit var gateway: WordSearchingUsecase

    private val stateSavingViewModel: StateSavingViewModel by viewModels()

    override val viewModel: WordSearchingViewModelContract.BaseViewModel
            by viewModels<WordSearchingViewModel> {
                WordSearchingViewModelFactory(gateway)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateSavingViewModel.data.observe(viewLifecycleOwner) {
            binding.lottie.alpha = it
        }

        requireActivity().app.appDependenciesComponent.inject(this)

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
    }

    private fun searchWord() {
        ifConnectedToInternet {
            hideKeyboard()
            viewModel.searchWord(binding.wordTranslateEditText.text.toString())
        }
    }

    override fun showTranslatingResult(result: List<SearchingResult>) {
        wordTranslateAdapter.setData(result)
        binding.wordTranslateRecyclerView.adapter = wordTranslateAdapter
    }

    override fun showTranslatingError(error: ErrorMapper) {
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

    override fun hideKeyboard() {
        binding.root.hideKeyboard()
    }

    override fun onDestroyView() {
        binding.wordTranslateRecyclerView.adapter = null
        super.onDestroyView()
    }

    private fun animateLottie(alpha: Float) {
        binding.lottie.animate()
            .alpha(alpha)
            .withEndAction {
                stateSavingViewModel.saveLottieVisibilityState(binding.lottie.alpha)
            }
            .duration = ANIMATION_DURATION
    }
}