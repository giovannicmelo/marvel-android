package com.marvel.characters.utils.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.marvel.characters.R

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(imageUrl)
            .error(R.drawable.layer_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}