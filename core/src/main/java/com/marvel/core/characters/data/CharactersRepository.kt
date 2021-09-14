package com.marvel.core.characters.data

import com.marvel.core.characters.data.contracts.CharactersDataSource
import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.domain.Character

class CharactersRepository(
    private val remoteDataSource: CharactersDataSource.Remote,
    private val localDataSource: CharactersDataSource.Local
) : CharactersRepositoryContract {
    override suspend fun fetchCharactersList(
        nextPage: Boolean,
        currentPage: Int,
        isRefresh: Boolean
    ): List<Character> {
        return if (isRefresh) {
            getListFromServer(nextPage, currentPage)
        } else {
            val persistedList = localDataSource.getCharacters()
            if (persistedList.isEmpty()) {
                getListFromServer(nextPage, currentPage)
            } else persistedList
        }
    }

    private suspend fun getListFromServer(nextPage: Boolean, currentPage: Int): List<Character> {
        val previousList = localDataSource.getCharacters()
        val remoteList = remoteDataSource.getCharacters(nextPage, currentPage)
        val newList = mutableListOf<Character>().also {
            it.addAll(previousList)
            it.addAll(remoteList)
        }
        localDataSource.saveCharacters(newList)
        return remoteList
    }

    override suspend fun fetchCharactersByName(nameStartsWith: String): List<Character> {
        return remoteDataSource.getCharactersByName(nameStartsWith)
    }

    override suspend fun getCharacterDetails(characterId: Int): Character {
        return localDataSource.getCharacters().find { it.id == characterId }
            ?: remoteDataSource.getCharacterById(characterId)
    }
}