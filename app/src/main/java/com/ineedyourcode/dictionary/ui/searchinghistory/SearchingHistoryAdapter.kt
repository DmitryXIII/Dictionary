package com.ineedyourcode.dictionary.ui.searchinghistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity
import com.ineedyourcode.dictionary.databinding.FragmentSearchingHistoryItemBinding

class SearchingHistoryAdapter(private val listener: (SearchingHistoryEntity) -> Unit) :
    RecyclerView.Adapter<SearchingHistoryAdapter.SearchingHistoryViewHolder>() {
    private var dataList = listOf<SearchingHistoryEntity>()

    fun setData(list: List<SearchingHistoryEntity>) {
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
        fun bind(historyEntity: SearchingHistoryEntity) {
            FragmentSearchingHistoryItemBinding.bind(itemView).apply {
                historyItemTitleTextView.text = historyEntity.translation
                historyFavoriteIconImageView.setImageResource(when (historyEntity.isFavorite) {
                    true -> android.R.drawable.star_big_on
                    false -> android.R.drawable.star_big_off
                })
                var isFavorite = historyEntity.isFavorite

                historyFavoriteIconImageView.setOnClickListener {
                    listener.invoke(historyEntity)

                    historyFavoriteIconImageView.setImageResource(when (isFavorite) {
                        true -> {
                            android.R.drawable.star_big_off
                        }
                        false -> {
                            android.R.drawable.star_big_on
                        }
                    })
                    isFavorite = !isFavorite
//                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }
}