package com.marvel.core.characters.usecases

import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.domain.Character

class FetchAllCharactersUseCase(private val repository: CharactersRepositoryContract) {

    suspend operator fun invoke(): List<Character> = repository.fetchAllCharacters()
}