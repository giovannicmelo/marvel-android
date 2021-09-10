package com.marvel.core.characters.usecases

import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.domain.Character

class FetchAllCharactersByNameUseCase(private val repository: CharactersRepositoryContract) {

    suspend operator fun invoke(nameStartsWith: String): List<Character> =
        repository.fetchAllCharactersByName(nameStartsWith)
}