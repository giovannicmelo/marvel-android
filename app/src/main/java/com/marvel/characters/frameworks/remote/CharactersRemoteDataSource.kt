package com.marvel.characters.frameworks.remote

import com.marvel.characters.frameworks.api.CharactersApi
import com.marvel.characters.frameworks.dtos.CharacterDto
import com.marvel.characters.frameworks.dtos.ResultDto
import com.marvel.characters.frameworks.utils.NetworkErrorException
import com.marvel.characters.frameworks.utils.request
import com.marvel.characters.mappers.CharacterMapper
import com.marvel.core.characters.data.contracts.CharactersDataSource
import com.marvel.core.characters.domain.Character

class CharactersRemoteDataSource(
    private val api: CharactersApi,
    private val apiKey: String,
    private val timestamp: String,
    private val hash: String
) : CharactersDataSource {

    @Throws(NetworkErrorException::class)
    override suspend fun getCharacters(): List<Character> {
        val result: ResultDto<CharacterDto> = request {
            api.getCharacters(apiKey, hash, timestamp)
        }!!
        return CharacterMapper.toEntityList(result)
    }

    @Throws(NetworkErrorException::class)
    override suspend fun getCharactersByName(nameStartsWith: String): List<Character> {
        val result: ResultDto<CharacterDto> = request {
            api.getCharactersWithQuery(apiKey, hash, timestamp, nameStartsWith)
        }!!
        return CharacterMapper.toEntityList(result)
    }

    @Throws(NetworkErrorException::class)
    override suspend fun getCharacterById(characterId: Int): Character {
        val result: ResultDto<CharacterDto> = request {
            api.getCharacterById(characterId, apiKey, hash, timestamp)
        }!!
        return CharacterMapper.toEntityList(result).first()
    }
}