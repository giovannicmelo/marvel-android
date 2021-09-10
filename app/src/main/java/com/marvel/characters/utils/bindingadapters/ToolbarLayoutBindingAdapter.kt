package com.marvel.characters.utils.bindingadapters

import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.marvel.characters.R

@BindingAdapter("font")
fun CollapsingToolbarLayout.font(@FontRes fontRes: Int? = null) {
    val typeface = ResourcesCompat.getFont(context, fontRes ?: R.font.open_sans_light)
    setExpandedTitleTypeface(typeface)
    setCollapsedTitleTypeface(typeface)
    setExpandedTitleColor(ContextCompat.getColor(context, R.color.white_gray))
    setCollapsedTitleTextColor(ContextCompat.getColor(context, android.R.color.black))
}