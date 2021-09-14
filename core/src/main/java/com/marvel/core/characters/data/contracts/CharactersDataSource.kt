package com.marvel.core.characters.data.contracts

import com.marvel.core.characters.domain.Character

interface CharactersDataSource {

    interface Local {
        fun getCharacters(): List<Character>

        fun saveCharacters(characters: List<Character>)

        fun deleteCharacters()
    }

    interface Remote {
        suspend fun getCharacters(
            nextPage: Boolean = false,
            currentPage: Int = 0
        ): List<Character>

        suspend fun getCharactersByName(
            nameStartsWith: String
        ): List<Character>

        suspend fun getCharacterById(
            characterId: Int
        ): Character
    }
}