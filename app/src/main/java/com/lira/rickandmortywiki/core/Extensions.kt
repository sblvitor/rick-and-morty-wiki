package com.lira.rickandmortywiki.core

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.lira.rickandmortywiki.R

var TextInputLayout.text: String
    get() = editText?.text?.toString() ?: ""
    set(value) {
        editText?.setText(value)
    }

fun View.hideSoftKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Fragment.createDialog(block: MaterialAlertDialogBuilder.() -> Unit = {}): AlertDialog {
    val builder = activity?.let { MaterialAlertDialogBuilder(it) }
    builder!!.setPositiveButton(android.R.string.ok, null)
    block(builder)
    return builder.create()
}

fun Fragment.createProgressDialog(): AlertDialog {
    return createDialog {
        val padding = this@createProgressDialog.resources.getDimensionPixelOffset(R.dimen.padding_default)
        val progressBar = ProgressBar(activity)
        progressBar.setPadding(padding, padding, padding, padding)
        setView(progressBar)

        setPositiveButton(null, null)
        setCancelable(false)
    }
}

fun translateResponseText(text: String): Int {
    return when(text){
        "Alive" -> R.string.response_alive
        "Dead" -> R.string.response_dead
        "unknown" -> R.string.response_unknown
        "Human" -> R.string.response_human
        "Male" -> R.string.response_male
        "Female" -> R.string.response_female
        "Genderless" -> R.string.response_genderless
        else -> R.string.blank
    }
}