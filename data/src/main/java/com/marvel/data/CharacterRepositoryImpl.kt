package com.marvel.data

import com.marvel.data.contracts.CharacterDataSource
import com.marvel.data.contracts.CharacterRepository
import com.marvel.data.dto.CharacterDTO

class CharacterRepositoryImpl(private val remoteDataSource: CharacterDataSource.Remote) :
    CharacterRepository {

    override suspend fun getAll(filter: String?): RepositoryObject<CharacterDTO> = request {
        remoteDataSource.getAllAsync(filter)
    }

    override suspend fun getById(id: Int): RepositoryObject<CharacterDTO> = request {
        remoteDataSource.getByIdAsync(id)
    }
}