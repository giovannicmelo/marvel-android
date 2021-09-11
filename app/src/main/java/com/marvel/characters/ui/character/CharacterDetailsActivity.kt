package com.marvel.characters.ui.character

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.appbar.AppBarLayout
import com.marvel.characters.R
import com.marvel.characters.base.BaseActivity
import com.marvel.characters.databinding.ActivityCharacterDetailsBinding
import com.marvel.characters.utils.extensions.animateTransitionOnRebind
import com.marvel.characters.utils.extensions.isConnected
import com.marvel.characters.utils.extensions.setBackIconColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class CharacterDetailsActivity :
    BaseActivity<ActivityCharacterDetailsBinding>(R.layout.activity_character_details) {

    private val viewModel: CharacterViewModel by viewModel()
    private val characterId by lazy {
        intent.getIntExtra(CharactersListActivity.CHARACTER_ID_EXTRA, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.animateTransitionOnRebind()
        setupActionBar()
        setupCollapsingLayout()
        loadCharacter()
    }

    private fun subscribeUi() {
        viewModel.character.observe(this, Observer {
            binding.character = it
            binding.isLoading = false
        })

        viewModel.comics.observe(this, Observer {
            it?.let {
                binding.rvComics.adapter = ComicAdapter(it)
                binding.rvComics.setHasFixedSize(true)
            }
        })

        viewModel.apiErrorLiveData.observe(this, Observer {
            Toast.makeText(this, it.second, Toast.LENGTH_LONG).show()
            binding.isLoading = false
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadCharacter() = lifecycleScope.launch {
        binding.isConnected = isConnected()
        binding.isLoading = isConnected()
        if (binding.isConnected) {
            viewModel.getCharacterById(characterId)
        } else {
            delay(500)
            binding.appbarCollapsing.appBarCollapsing.setExpanded(false, true)
        }
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