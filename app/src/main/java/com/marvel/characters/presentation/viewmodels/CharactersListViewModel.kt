package com.marvel.characters.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.marvel.characters.presentation.utils.state
import com.marvel.core.characters.usecases.FetchAllCharactersByNameUseCase
import com.marvel.core.characters.usecases.FetchAllCharactersUseCase

class CharactersListViewModel(
    private val fetchAllCharactersUseCase: FetchAllCharactersUseCase,
    private val fetchAllCharactersByNameUseCase: FetchAllCharactersByNameUseCase
) : ViewModel() {

    fun fetchCharactersList(query: String? = null) = state {
        if (query.isNullOrEmpty()) {
            fetchAllCharactersUseCase()
        } else {
            fetchAllCharactersByNameUseCase(query)
        }
    }
}