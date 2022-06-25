package com.ineedyourcode.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ineedyourcode.core.R
import com.ineedyourcode.dictionary.ui.uils.ActivityContract
import com.ineedyourcode.core.ui.uils.ErrorMapper
import com.ineedyourcode.core.ui.uils.hideKeyboard

abstract class BaseFragment<VB : ViewBinding, T>(
    private val inflateBinding: (
        inflater: LayoutInflater,
        root: ViewGroup?,
        attachToRoot: Boolean,
    ) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding!!

    protected lateinit var mainController: ActivityContract

    abstract val viewModel: ViewModelContract.BaseViewModel

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
        if (requireActivity() is ActivityContract) {
            mainController = requireActivity() as ActivityContract
        } else {
            throw IllegalStateException(getString(R.string.no_is_main_controller))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun showResult(result: T)
    abstract fun showError(error: ErrorMapper)
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
                showResult(state.result as T)
            }

            is AppState.Error -> {
                hideProgress()
                showError(state.error)
            }
        }
    }

    protected fun ifConnectedToInternet(action: () -> Unit) {
        if (mainController.checkInternet()) {
            binding.root.hideKeyboard()
            action()
        }
    }
}