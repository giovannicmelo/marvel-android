package com.marvel.core.characters.usecases

import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.domain.Character

class FetchCharactersByNameUseCase(private val repository: CharactersRepositoryContract) {

    suspend operator fun invoke(nameStartsWith: String): List<Character> =
        repository.fetchCharactersByName(nameStartsWith)
}