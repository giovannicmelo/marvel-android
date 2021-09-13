package com.marvel.core.characters.data

import com.marvel.core.characters.data.contracts.CharactersDataSource
import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.domain.Character

class CharactersRepository(private val dataSource: CharactersDataSource) :
    CharactersRepositoryContract {
    override suspend fun fetchCharactersList(
        nextPage: Boolean,
        currentPage: Int
    ): List<Character> {
        return dataSource.getCharacters(nextPage, currentPage)
    }

    override suspend fun fetchCharactersByName(nameStartsWith: String): List<Character> {
        return dataSource.getCharactersByName(nameStartsWith)
    }

    override suspend fun getCharacterDetails(characterId: Int): Character {
        return dataSource.getCharacterById(characterId)
    }
}