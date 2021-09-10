package com.marvel.characters.utils.extensions

import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.transition.AutoTransition
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionManager

fun <T : View> T.runTransition(
    duration: Long,
    block: T.() -> Unit,
    onCompletion: (() -> Unit)? = null
): T {
    val transition = AutoTransition()
    transition.duration = duration
    transition.interpolator = AccelerateDecelerateInterpolator()

    onCompletion?.run {
        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionResume(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                onCompletion()
            }
        })
    }

    TransitionManager.go(Scene(this.parent as ViewGroup), transition)
    block()
    return this
}