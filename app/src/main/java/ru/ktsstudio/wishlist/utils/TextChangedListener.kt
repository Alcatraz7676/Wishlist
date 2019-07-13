package ru.ktsstudio.wishlist.utils

import android.text.Editable
import android.text.TextWatcher

class TextChangedListener(private val func: () -> Unit) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        func()
    }

    override fun afterTextChanged(s: Editable?) {
    }
}