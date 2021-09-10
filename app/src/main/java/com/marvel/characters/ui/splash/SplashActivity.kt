package com.marvel.characters.ui.splash

import android.os.Bundle
import android.view.View
import com.marvel.characters.ui.character.CharactersListActivity
import com.marvel.characters.R
import com.marvel.characters.base.BaseActivity
import com.marvel.characters.databinding.ActivitySplashBinding
import com.marvel.characters.utils.extensions.launchActivity
import com.marvel.characters.utils.extensions.runTransition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    companion object {
        private const val splashTime = 1_500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launch {
            delay(200)
            binding.ivSplashImage.runTransition(splashTime,
                block = {
                    visibility = View.INVISIBLE
                },
                onCompletion = {
                    launchActivity<CharactersListActivity>()
                })
        }
    }
}