package com.marvel.core

import com.marvel.core.characters.data.CharactersRepository
import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.usecases.FetchCharactersByNameUseCase
import com.marvel.core.characters.usecases.FetchCharactersListUseCase
import com.marvel.core.characters.usecases.GetCharacterDetailsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

private val modules = module {

    factory<CharactersRepositoryContract> {
        CharactersRepository(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
    factory { FetchCharactersListUseCase(get()) }
    factory { FetchCharactersByNameUseCase(get()) }
    factory { GetCharacterDetailsUseCase(get()) }
}

fun getCoreModules(): List<Module> = listOf(modules)