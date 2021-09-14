package com.marvel.characters.frameworks.local

import com.marvel.characters.frameworks.local.dao.AbstractDao
import com.marvel.core.characters.data.contracts.CharactersDataSource
import com.marvel.core.characters.domain.Character

class CharactersLocalDataSource(private val dao: AbstractDao<List<Character>>) :
    CharactersDataSource.Local {

    override fun getCharacters(): List<Character> {
        return dao.getData() ?: listOf()
    }

    override fun saveCharacters(characters: List<Character>) {
        dao.setData(characters)
    }

    override fun deleteCharacters() {
        dao.deleteData()
    }
}