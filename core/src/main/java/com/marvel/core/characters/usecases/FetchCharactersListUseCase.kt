package com.marvel.core.characters.usecases

import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.domain.Character

class FetchCharactersListUseCase(private val repository: CharactersRepositoryContract) {

    suspend operator fun invoke(
        nextPage: Boolean = false,
        currentPage: Int = 0,
        isRefresh: Boolean = false
    ): List<Character> =
        repository.fetchCharactersList(nextPage, currentPage, isRefresh)
}