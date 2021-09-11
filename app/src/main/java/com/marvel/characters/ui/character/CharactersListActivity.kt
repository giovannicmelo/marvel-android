package com.marvel.characters.ui.character

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.R
import com.marvel.characters.base.BaseActivity
import com.marvel.characters.databinding.ActivityCharactersListBinding
import com.marvel.characters.utils.extensions.animateTransitionOnRebind
import com.marvel.characters.utils.extensions.hideKeyboard
import com.marvel.characters.utils.extensions.isConnected
import com.marvel.characters.utils.extensions.launchActivityForSharedElements
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersListActivity :
    BaseActivity<ActivityCharactersListBinding>(R.layout.activity_characters_list) {

    companion object {
        const val CHARACTER_ID_EXTRA = "characterId"
    }

    private val viewModel: CharacterViewModel by viewModel()
    private val adapter = CharacterAdapter(emptyList()) { character, image, text ->
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
        binding.clickListener = View.OnClickListener { view ->
            performClickListener(view)
        }
        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                loadCharacters(v.text.toString())
                true
            } else {
                false
            }
        }
        binding.fabScroll.hide()
        binding.animateTransitionOnRebind()
        setupRecycler()
        setupSwipe()

        loadCharacters(binding.etSearch.text.toString())
    }

    private fun subscribeUi() {
        viewModel.characters.observe(this, Observer {
            if (it != null) {
                // adapter.submitList(it)
                binding.isLoading = false
                binding.swipeCharacters.isRefreshing = false
            }
        })

        viewModel.apiErrorLiveData.observe(this, Observer {
            Toast.makeText(this, it.second.toString(), Toast.LENGTH_LONG).show()
            binding.isLoading = false
            binding.swipeCharacters.isRefreshing = false
        })
    }

    private fun performClickListener(view: View) {
        when (view.id) {
            binding.fabScroll.id -> binding.rvCharacters.smoothScrollToPosition(0)
            binding.btTryAgain.id -> loadCharacters(binding.etSearch.text.toString())
        }
    }

    private fun loadCharacters(query: String? = null) {
        binding.isConnected = isConnected()
        binding.isLoading = isConnected()
        binding.swipeCharacters.isRefreshing = !binding.isLoading

        if (binding.isLoading) {
            adapter.submitList(emptyList())
        }

        if (binding.isConnected) {
            viewModel.getCharacters(query)
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
}
