package com.ineedyourcode.dictionary.ui.wordsearching

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ineedyourcode.dictionary.databinding.FragmentSearchResultItemBinding
import com.ineedyourcode.dictionary.domain.entity.SearchingResult

class WordSearchingFragmentRecyclerViewAdapter(private val clickListener : ((SearchingResult) -> Unit)) :
    RecyclerView.Adapter<WordSearchingFragmentRecyclerViewAdapter.WordTranslatingViewHolder>() {
    private var dataList = listOf<SearchingResult>()

    fun setData(resultList: List<SearchingResult>) {
        dataList = listOf()
        notifyItemRangeRemoved(0, dataList.lastIndex)
        dataList = resultList
        notifyItemRangeInserted(0, dataList.lastIndex)
    }

    fun clearData() {
        dataList = listOf()
        notifyItemRangeRemoved(0, dataList.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordTranslatingViewHolder {
        val binding = FragmentSearchResultItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return WordTranslatingViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WordTranslatingViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner  class WordTranslatingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(searchingResult: SearchingResult) {
            FragmentSearchResultItemBinding.bind(itemView).apply {
                translationTextView.text = searchingResult.wordTranslation
            }

            itemView.setOnClickListener {
                clickListener.invoke(searchingResult)
            }
        }
    }
}

