package com.marvel.core

import com.marvel.core.characters.data.CharactersRepository
import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.marvel.core.characters.usecases.FetchAllCharactersByNameUseCase
import com.marvel.core.characters.usecases.FetchAllCharactersUseCase
import com.marvel.core.characters.usecases.GetCharacterDetailsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

private val modules = module {

    factory<CharactersRepositoryContract> { CharactersRepository(get()) }
    factory { FetchAllCharactersUseCase(get()) }
    factory { FetchAllCharactersByNameUseCase(get()) }
    factory { GetCharacterDetailsUseCase(get()) }
}

fun getCoreModules(): List<Module> = listOf(modules)