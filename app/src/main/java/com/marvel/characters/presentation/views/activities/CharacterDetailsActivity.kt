package com.marvel.characters.presentation.views.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.AppBarLayout
import com.marvel.characters.R
import com.marvel.characters.base.BaseActivity
import com.marvel.characters.databinding.ActivityCharacterDetailsBinding
import com.marvel.characters.frameworks.utils.State
import com.marvel.characters.presentation.viewmodels.CharacterDetailsViewModel
import com.marvel.characters.presentation.views.adapters.ComicsListAdapter
import com.marvel.characters.utils.extensions.animateTransitionOnRebind
import com.marvel.characters.utils.extensions.isConnected
import com.marvel.characters.utils.extensions.setBackIconColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class CharacterDetailsActivity :
    BaseActivity<ActivityCharacterDetailsBinding>(R.layout.activity_character_details) {

    private val characterId by lazy { intent.getIntExtra(CHARACTER_ID_EXTRA, 0) }

    private val viewModel: CharacterDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViews() {
        binding.animateTransitionOnRebind()
        binding.isConnected = isConnected()
        setupActionBar()
        setupCollapsingLayout()
        lifecycleScope.launch {
            if (binding.isConnected) {
                loadCharacter(characterId)
            } else {
                delay(500)
                binding.appbarCollapsing.appBarCollapsing.setExpanded(false, true)
            }
        }
    }

    private fun loadCharacter(id: Int) {
        viewModel.getCharacter(id).observe(this, { state ->
            when (state) {
                is State.LoadingState -> {
                    binding.isLoading = true
                }
                is State.DataState -> {
                    binding.isLoading = false
                    binding.character = state.data
                    state.data.comics?.let {
                        binding.rvComics.adapter = ComicsListAdapter(it)
                        binding.rvComics.setHasFixedSize(true)
                    }
                }
                is State.ErrorState -> {
                    Toast.makeText(this, state.exception.localizedMessage, Toast.LENGTH_LONG).show()
                    binding.isLoading = false
                }
            }
        })
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.appbarCollapsing.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupCollapsingLayout() {
        binding.appbarCollapsing.appBarCollapsing.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) >= appBarLayout.totalScrollRange / 2) {
                    supportActionBar?.setBackIconColor(android.R.color.black)
                } else {
                    supportActionBar?.setBackIconColor(android.R.color.white)
                }
            })
    }
}