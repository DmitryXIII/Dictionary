package com.ineedyourcode.dictionary.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ineedyourcode.dictionary.databinding.FragmentSearchingHistoryItemBinding
import com.ineedyourcode.domain.entity.HistoryItem
import com.ineedyourcode.dictionary.ui.uils.setFavoriteIcon

class SearchingHistoryAdapter(
    private val onItemClickListener: (HistoryItem) -> Unit,
    private val onFavoriteIconClickListener: (HistoryItem) -> Unit,
) :
    RecyclerView.Adapter<SearchingHistoryAdapter.SearchingHistoryViewHolder>() {
    private var dataList = listOf<HistoryItem>()

    fun setData(list: List<HistoryItem>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchingHistoryViewHolder {
        return SearchingHistoryViewHolder((FragmentSearchingHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)).root)
    }

    override fun onBindViewHolder(holder: SearchingHistoryViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner class SearchingHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(historyItem: HistoryItem) {
            FragmentSearchingHistoryItemBinding.bind(itemView).apply {
                historyItemTitleTextView.text = historyItem.word
                historyFavoriteIconImageView.setFavoriteIcon(historyItem)

                historyFavoriteIconImageView.setOnClickListener {
                    onFavoriteIconClickListener.invoke(historyItem)
                }

                itemView.setOnClickListener {
                    onItemClickListener.invoke(historyItem)
                }
            }
        }
    }
}