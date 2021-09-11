package com.marvel.characters.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.marvel.characters.presentation.utils.state
import com.marvel.core.characters.usecases.GetCharacterDetailsUseCase

class CharacterDetailsViewModel(private val getCharacterDetails: GetCharacterDetailsUseCase) :
    ViewModel() {

    fun getCharacter(id: Int) = state {
        getCharacterDetails(id)
    }
}