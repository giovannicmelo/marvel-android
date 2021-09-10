package com.marvel.core.characters.data.contracts

import com.marvel.core.characters.domain.Character

interface CharactersRepositoryContract {

    suspend fun fetchAllCharacters(): List<Character>

    suspend fun fetchAllCharactersByName(nameStartsWith: String): List<Character>

    suspend fun getCharacterDetails(characterId: Int): Character
}