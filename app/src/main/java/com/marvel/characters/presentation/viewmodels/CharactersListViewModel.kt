package com.marvel.characters.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.marvel.characters.presentation.utils.state
import com.marvel.core.characters.usecases.FetchCharactersByNameUseCase
import com.marvel.core.characters.usecases.FetchCharactersListUseCase

class CharactersListViewModel(
    private val fetchCharactersList: FetchCharactersListUseCase,
    private val fetchCharactersByName: FetchCharactersByNameUseCase
) : ViewModel() {

    fun fetchCharacters(query: String? = null, nextPage: Boolean = false, currentPage: Int = 0) =
        state {
            if (query.isNullOrEmpty()) {
                fetchCharactersList(nextPage, currentPage)
            } else {
                fetchCharactersByName(query)
            }
        }
}