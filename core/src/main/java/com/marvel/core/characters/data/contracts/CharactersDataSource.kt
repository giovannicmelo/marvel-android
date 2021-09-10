package com.marvel.core.characters.data.contracts

import com.marvel.core.characters.domain.Character

interface CharactersDataSource {

    suspend fun getCharacters(): List<Character>

    suspend fun getCharactersByName(
        nameStartsWith: String
    ): List<Character>

    suspend fun getCharacterById(
        characterId: Int
    ): Character
}