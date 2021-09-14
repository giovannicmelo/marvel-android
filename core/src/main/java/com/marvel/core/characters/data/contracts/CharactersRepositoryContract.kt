package com.marvel.core.characters.data.contracts

import com.marvel.core.characters.domain.Character

interface CharactersRepositoryContract {

    suspend fun fetchCharactersList(
        nextPage: Boolean = false,
        currentPage: Int = 0,
        isRefresh: Boolean = false
    ): List<Character>

    suspend fun fetchCharactersByName(nameStartsWith: String): List<Character>

    suspend fun getCharacterDetails(characterId: Int): Character
}