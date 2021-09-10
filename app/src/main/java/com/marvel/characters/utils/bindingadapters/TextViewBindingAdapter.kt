package com.marvel.characters.utils.bindingadapters

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("renderHtml")
fun TextView.bindRenderHtml(description: String?) {
    if (description != null) {
        text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        movementMethod = LinkMovementMethod.getInstance()
    } else {
        text = ""
    }
}