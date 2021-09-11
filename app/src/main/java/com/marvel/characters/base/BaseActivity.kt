package com.marvel.characters.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.marvel.characters.utils.extensions.activityBinding

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes val resId: Int) : AppCompatActivity() {

    val binding by activityBinding<T>(resId)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }
}
