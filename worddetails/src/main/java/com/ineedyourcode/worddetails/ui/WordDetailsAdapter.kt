package com.ineedyourcode.worddetails.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ineedyourcode.domain.entity.WordMeaning
import com.ineedyourcode.worddetails.databinding.FragmentWordDetailsItemBinding

private const val IMAGE_URL_PREFIX = "https:"

class WordDetailsAdapter : RecyclerView.Adapter<WordDetailsViewHolder>() {
    private var dataList: List<WordMeaning> = listOf()

    fun setData(meaningsList: List<WordMeaning>) {
        dataList = meaningsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordDetailsViewHolder {
        return WordDetailsViewHolder(FragmentWordDetailsItemBinding.inflate(LayoutInflater.from(
            parent.context), parent, false).root)
    }

    override fun onBindViewHolder(holder: WordDetailsViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}

class WordDetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(wordMeaning: WordMeaning) {
        FragmentWordDetailsItemBinding.bind(itemView).apply {
            translationTextView.text = wordMeaning.translation
            transcriptionTextView.text = wordMeaning.transcription
            exampleNoteTextView.text = wordMeaning.note
            wordImageView.load("$IMAGE_URL_PREFIX${wordMeaning.imageUrl}")
        }
    }
}