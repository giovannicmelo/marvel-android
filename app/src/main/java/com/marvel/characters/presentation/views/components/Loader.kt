package com.marvel.characters.presentation.views.components

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.marvel.characters.R

class Loader(
    context: Context,
    var viewGroup: ViewGroup?
) {
    private var active: Boolean = false
    private var layout: ConstraintLayout = ConstraintLayout(context)

    init {
        layout = View.inflate(context, R.layout.loader_animated_layout, null) as ConstraintLayout
    }

    val isStarted: Boolean get() = active

    fun start() {
        if (active) return
        active = true
        layout.visibility = View.VISIBLE
        viewGroup?.addView(layout)
    }

    fun stop() {
        if (!active) return
        active = false
        layout.visibility = View.GONE
        viewGroup?.removeView(layout)
        layout.invalidate()
    }
}