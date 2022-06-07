package com.ineedyourcode.dictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.domain.entity.SearchingResult
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingState
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingViewModelContract

abstract class BaseFragment<VB : ViewBinding>(
    private val inflateBinding: (
        inflater: LayoutInflater,
        root: ViewGroup?,
        attachToRoot: Boolean,
    ) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    private lateinit var connectionChecker: InternetConnectionChecker

    abstract val viewModel: WordSearchingViewModelContract.BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = inflateBinding.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireActivity() is InternetConnectionChecker) {
            connectionChecker = requireActivity() as InternetConnectionChecker
        } else {
            throw IllegalStateException(getString(R.string.no_is_internet_connection_checker))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun showTranslatingResult(result: List<SearchingResult>)
    abstract fun showTranslatingError(error: ErrorMapper)

    protected fun renderData(state: WordSearchingState) {
        when(state) {
            WordSearchingState.Loading -> {
                showProgress()
            }

            is WordSearchingState.Success -> {
                hideProgress()
                showTranslatingResult(state.searchResult)
            }

            is WordSearchingState.Error -> {
                hideProgress()
                showTranslatingError(state.error)
            }
        }
    }

    abstract fun showProgress()
    abstract fun hideProgress()
    abstract fun hideKeyboard()

    protected fun ifConnectedToInternet(action: () -> Unit) {
        if (connectionChecker.checkInternet()) {
            action()
        }
    }
}