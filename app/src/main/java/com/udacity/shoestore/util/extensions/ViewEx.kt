package com.udacity.shoestore.util.extensions

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

inline fun Fragment.toast(message: Int): Toast = Toast
    .makeText(this.requireContext(), message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

inline fun Fragment.longToast(message: String): Toast = Toast
    .makeText(this.requireContext(), message, Toast.LENGTH_LONG)
    .apply {
        show()
    }