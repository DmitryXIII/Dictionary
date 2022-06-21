package com.ineedyourcode.dictionary.ui.searchinghistory

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity
import com.ineedyourcode.dictionary.databinding.FragmentSearchingHistoryBinding
import com.ineedyourcode.dictionary.ui.BaseFragment
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import com.ineedyourcode.dictionary.ui.uils.showErrorSnack
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchingHistoryFragment :
    BaseFragment<FragmentSearchingHistoryBinding, List<HistoryEntity>>(
        FragmentSearchingHistoryBinding::inflate) {
    override val viewModel: SearchingHistoryViewModel by viewModel()

    private val historyAdapter =
        SearchingHistoryAdapter { searchingHistoryEntity ->

        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getSearchingHistory()
    }

    override fun showResult(result: List<HistoryEntity>) {
        historyAdapter.setData(result)
    }

    override fun showError(error: ErrorMapper) {
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