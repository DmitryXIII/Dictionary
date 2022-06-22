package com.ineedyourcode.dictionary.ui.history

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ineedyourcode.dictionary.databinding.FragmentSearchingHistoryBinding
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.ui.BaseFragment
import com.ineedyourcode.dictionary.ui.uils.ErrorMapper
import com.ineedyourcode.dictionary.ui.uils.showErrorSnack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val DEFAULT_QUERY_VALUE = ""

class SearchingHistoryFragment :
    BaseFragment<FragmentSearchingHistoryBinding, List<HistoryItem>>(
        FragmentSearchingHistoryBinding::inflate) {
    override val viewModel: SearchingHistoryViewModel by viewModel()

    private val queryFlow = MutableStateFlow(DEFAULT_QUERY_VALUE)

    private val onItemClickAction = { historyItem: HistoryItem ->
        mainController.openWordDetailsFromHistory(historyItem)
    }

    private val onFavoriteIconClickListener = { historyItem: HistoryItem ->
        viewModel.updateFavorite(historyItem)
    }

    private val historyAdapter =
        SearchingHistoryAdapter(onItemClickAction, onFavoriteIconClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }

        binding.historySearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    queryFlow.value = query
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    queryFlow.value = newText
                }
                return true
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                queryFlow
                    .debounce(300)
                    .flatMapLatest { viewModel.searchInHistory(it) }
                    .collect { showResult(it) }
            }
        }
    }

    override fun showResult(result: List<HistoryItem>) {
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