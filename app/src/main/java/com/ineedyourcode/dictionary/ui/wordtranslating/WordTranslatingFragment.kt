package com.ineedyourcode.dictionary.ui.wordtranslating

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.databinding.FragmentWordTraslatingBinding
import com.ineedyourcode.dictionary.domain.entity.TranslationResult
import com.ineedyourcode.dictionary.ui.hideKeyboard
import com.ineedyourcode.dictionary.ui.showSnack

class WordTranslatingFragment : Fragment(R.layout.fragment_word_traslating),
    WordTranslatingViewContract {
    override val errorMapper = ErrorMapper()

    private var _binding: FragmentWordTraslatingBinding? = null
    private val binding: FragmentWordTraslatingBinding
        get() = _binding!!

    private val presenter: WordTranslatingFragmentPresenterContract =
        WordTranslatingFragmentPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWordTraslatingBinding.bind(view)

        presenter.onAttach(this)

        binding.wordTranslateInputLayout.setEndIconOnClickListener {
            hideKeyboard()
            presenter.searchWord(binding.wordTranslateEditText.text.toString())
        }

        binding.wordTranslateEditText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    hideKeyboard()
                    presenter.searchWord(binding.wordTranslateEditText.text.toString())
                }
            }
            false
        }
    }

    override fun showTranslatingResult(result: List<TranslationResult>) {
        Toast.makeText(requireContext(), result.first().wordTranslation, Toast.LENGTH_SHORT).show()
    }

    override fun showTranslatingError(error: String) {
        binding.root.showSnack(errorMapper.getErrorMessage(requireContext(), error))
    }

    override fun showProgress() {
        binding.translateWordProgressBar.isVisible = true
    }

    override fun hideProgress() {
        binding.translateWordProgressBar.isVisible = false
    }

    override fun hideKeyboard() {
        binding.root.hideKeyboard()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}