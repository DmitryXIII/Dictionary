package com.ineedyourcode.dictionary.ui.wordtranslating

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.databinding.FragmentWordTraslatingBinding
import com.ineedyourcode.dictionary.domain.entity.TranslationResult

class WordTranslatingFragment : Fragment(R.layout.fragment_word_traslating),
    WordTranslatingViewContract {
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
            presenter.searchWord(binding.wordTranslateEditText.text.toString())
        }
    }

    override fun showTranslatingResult(result: List<TranslationResult>) {
        Toast.makeText(requireContext(), result.first().wordTranslation, Toast.LENGTH_SHORT).show()
    }

    override fun showTranslatingError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.translateWordProgressBar.isVisible = true
    }

    override fun hideProgress() {
        binding.translateWordProgressBar.isVisible = false
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