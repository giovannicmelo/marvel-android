package com.marvel.data.contracts.abstracts

import com.marvel.data.RepositoryObject

interface Repository<T> {

    suspend fun getAll(filter: String? = null): RepositoryObject<T>
    suspend fun getById(id: Int): RepositoryObject<T>
}