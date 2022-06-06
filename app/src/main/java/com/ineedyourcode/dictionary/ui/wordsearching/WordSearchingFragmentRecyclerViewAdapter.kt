package com.ineedyourcode.dictionary.ui.wordsearching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ineedyourcode.dictionary.databinding.SearchResultItemBinding
import com.ineedyourcode.dictionary.domain.entity.SearchingResult

class WordSearchingFragmentRecyclerViewAdapter :
    RecyclerView.Adapter<WordTranslatingViewHolder>() {
    private var dataList = listOf<SearchingResult>()

    fun setData(resultList: List<SearchingResult>) {
        dataList = listOf()
        notifyItemRangeRemoved(0, dataList.size - 1)
        dataList = resultList
        notifyItemRangeInserted(0, dataList.size - 1)
    }

    fun clearData() {
        dataList = listOf()
        notifyItemRangeRemoved(0, dataList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordTranslatingViewHolder {
        val binding = SearchResultItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return WordTranslatingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WordTranslatingViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}

class WordTranslatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(searchingResult: SearchingResult) {
        SearchResultItemBinding.bind(itemView).apply {
            translatedWordTextView.text = searchingResult.wordTranslation
            translatedWordDescriptionTextView.text = searchingResult.wordDescription
        }
    }
}