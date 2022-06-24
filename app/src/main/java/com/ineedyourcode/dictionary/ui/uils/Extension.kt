package com.ineedyourcode.dictionary.ui.uils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.google.android.material.snackbar.Snackbar
import com.ineedyourcode.dictionary.domain.entity.HistoryItem
import kotlinx.coroutines.flow.MutableStateFlow

fun View.showErrorSnack(message: ErrorMapper) {
    val mappedMessage = when (message) {
        is ErrorMapper.DirectString -> {
            message.getMessage()
        }
        is ErrorMapper.StringResource -> {
            this.context.getString(message.getMessage())
        }
    }
    Snackbar.make(this, mappedMessage, Snackbar.LENGTH_SHORT).show()
}

fun View.hideKeyboard(): Boolean {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun SearchView.setOnTypeTextListener(stateFlow: MutableStateFlow<String>) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                stateFlow.value = query
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let {
                stateFlow.value = newText
            }
            return true
        }
    })
}

fun ImageView.setFavoriteIcon(historyItem: HistoryItem) {
    this.setImageResource(when (historyItem.isFavorite) {
        true -> android.R.drawable.star_on
        false -> android.R.drawable.star_off
    })
}