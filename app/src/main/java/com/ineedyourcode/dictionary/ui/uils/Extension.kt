package com.ineedyourcode.dictionary.ui.uils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

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