package com.marvel.characters.presentation.views.activities

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.R
import com.marvel.characters.base.BaseActivity
import com.marvel.characters.databinding.ActivityCharactersListBinding
import com.marvel.characters.frameworks.utils.State
import com.marvel.characters.presentation.viewmodels.CharactersListViewModel
import com.marvel.characters.presentation.views.adapters.CharactersListAdapter
import com.marvel.characters.utils.extensions.hideKeyboard
import com.marvel.characters.utils.extensions.isConnected
import com.marvel.characters.utils.extensions.launchActivityForSharedElements
import org.koin.androidx.viewmodel.ext.android.viewModel

const val CHARACTER_ID_EXTRA = "characterId"

class CharactersListActivity :
    BaseActivity<ActivityCharactersListBinding>(R.layout.activity_characters_list) {

    private val viewModel: CharactersListViewModel by viewModel()

    private val adapter = CharactersListAdapter(emptyList()) { character, image, text ->
        launchActivityForSharedElements<CharacterDetailsActivity>(
            Bundle().apply {
                putInt(
                    CHARACTER_ID_EXTRA,
                    character.id ?: 0
                )
            },
            Pair(image, getString(R.string.image_transition_name)),
            Pair(text, getString(R.string.text_transition_name))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        configureListeners()
    }

    private fun setupViews() {
        binding.isConnected = isConnected()
        setupRecycler()
        setupSwipe()
        loadCharacters()
    }

    private fun configureListeners() {
        binding.clickListener = View.OnClickListener { view ->
            when (view.id) {
                binding.fabScroll.id -> binding.rvCharacters.smoothScrollToPosition(0)
                binding.btTryAgain.id -> loadCharacters(binding.etSearch.text.toString())
            }
        }
        binding.etSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                loadCharacters(v.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun setupSwipe() {
        binding.run {
            swipeCharacters.setColorSchemeColors(
                ContextCompat.getColor(this@CharactersListActivity, R.color.deep_red_two)
            )
            swipeCharacters.setOnRefreshListener {
                adapter.submitList(emptyList())
                loadCharacters(binding.etSearch.text.toString())
            }
        }
    }

    private fun setupRecycler() {
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.setHasFixedSize(true)
        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && binding.fabScroll.visibility != View.VISIBLE) {
                    binding.fabScroll.show()
                } else if (dy < 0 && binding.fabScroll.visibility == View.VISIBLE) {
                    binding.fabScroll.hide()
                }
            }
        })
    }

    private fun loadCharacters(query: String? = null) {
        viewModel.fetchCharactersList(query).observe(this, { state ->
            when (state) {
                is State.LoadingState -> {
                    binding.isLoading = true
                    binding.isEmpty = false
                    binding.swipeCharacters.isRefreshing = !binding.isLoading
                    adapter.submitList(emptyList())
                }
                is State.DataState -> {
                    binding.isLoading = false
                    binding.swipeCharacters.isRefreshing = false
                    binding.isEmpty = state.data.isEmpty()
                    adapter.submitList(state.data)
                }
                is State.ErrorState -> {
                    Toast.makeText(this, state.exception.localizedMessage, Toast.LENGTH_LONG).show()
                    binding.isLoading = false
                    binding.isEmpty = false
                    binding.swipeCharacters.isRefreshing = false
                }
            }
        })
    }
}