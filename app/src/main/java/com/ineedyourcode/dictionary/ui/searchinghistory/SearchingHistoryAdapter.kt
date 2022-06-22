package com.ineedyourcode.dictionary.ui.searchinghistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ineedyourcode.dictionary.data.datasource.local.entities.HistoryEntity
import com.ineedyourcode.dictionary.databinding.FragmentSearchingHistoryItemBinding
import com.ineedyourcode.dictionary.domain.entity.HistoryItem

class SearchingHistoryAdapter(private val listener: (HistoryItem) -> Unit) :
    RecyclerView.Adapter<SearchingHistoryAdapter.SearchingHistoryViewHolder>() {
    private var dataList = listOf<HistoryItem>()

    fun setData(list: List<HistoryItem>) {
        dataList = list
        notifyItemRangeInserted(0, dataList.lastIndex)
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
                historyFavoriteIconImageView.setImageResource(when (historyItem.isFavorite) {
                    true -> android.R.drawable.star_big_on
                    false -> android.R.drawable.star_big_off
                })
                var isFavorite = historyItem.isFavorite

                historyFavoriteIconImageView.setOnClickListener {
                    listener.invoke(historyItem)

                    historyFavoriteIconImageView.setImageResource(when (isFavorite) {
                        true -> {
                            android.R.drawable.star_big_off
                        }
                        false -> {
                            android.R.drawable.star_big_on
                        }
                    })
                    isFavorite = !isFavorite
                }
            }
        }
    }
}