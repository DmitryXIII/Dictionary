package com.ineedyourcode.dictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import com.ineedyourcode.dictionary.ui.uils.hideKeyboard
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingViewModelContract

abstract class BaseFragment<VB : ViewBinding, T>(
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

    abstract fun showTranslatingResult(result: T)
    abstract fun showTranslatingError(error: ErrorMapper)
    abstract fun showProgress()
    abstract fun hideProgress()

    protected fun renderData(state: AppState) {
        when (state) {
            AppState.Loading -> {
                showProgress()
            }

            is AppState.Success -> {
                hideProgress()
                @Suppress("UNCHECKED_CAST")
                showTranslatingResult(state.result as T)
            }

            is AppState.Error -> {
                hideProgress()
                showTranslatingError(state.error)
            }
        }
    }

    protected fun ifConnectedToInternet(action: () -> Unit) {
        if (connectionChecker.checkInternet()) {
            binding.root.hideKeyboard()
            action()
        }
    }
}