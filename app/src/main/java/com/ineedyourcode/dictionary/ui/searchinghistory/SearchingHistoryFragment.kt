package com.ineedyourcode.dictionary.ui.searchinghistory

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity
import com.ineedyourcode.dictionary.databinding.FragmentSearchingHistoryBinding
import com.ineedyourcode.dictionary.ui.BaseFragment
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import com.ineedyourcode.dictionary.ui.uils.showErrorSnack
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchingHistoryFragment :
    BaseFragment<FragmentSearchingHistoryBinding, List<SearchingHistoryEntity>>(
        FragmentSearchingHistoryBinding::inflate) {
    override val viewModel: SearchingHistoryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getSearchingHistory()
    }

    override fun showResult(result: List<SearchingHistoryEntity>) {

    }

    override fun showError(error: ErrorMapper) {
        binding.historyProgressBar.isVisible = false
        binding.root.showErrorSnack(error)
    }

    override fun showProgress() {
        binding.historyProgressBar.isVisible = true
        binding.historyRecyclerView.isVisible = false
    }

    override fun hideProgress() {
        binding.historyProgressBar.isVisible = false
        binding.historyRecyclerView.isVisible = true
    }
}