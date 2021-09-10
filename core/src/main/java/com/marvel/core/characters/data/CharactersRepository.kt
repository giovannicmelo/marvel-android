package com.marvel.core.characters.data

import com.marvel.core.characters.data.contracts.CharactersDataSource
import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.domain.Character

class CharactersRepository(private val dataSource: CharactersDataSource) :
    CharactersRepositoryContract {
    override suspend fun fetchAllCharacters(): List<Character> {
        return dataSource.getCharacters()
    }

    override suspend fun fetchAllCharactersByName(nameStartsWith: String): List<Character> {
        return dataSource.getCharactersByName(nameStartsWith)
    }

    override suspend fun getCharacterDetails(characterId: Int): Character {
        return dataSource.getCharacterById(characterId)
    }
}