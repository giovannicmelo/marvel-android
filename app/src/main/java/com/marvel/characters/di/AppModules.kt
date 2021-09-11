package com.marvel.characters.di

import com.marvel.characters.BuildConfig
import com.marvel.characters.frameworks.api.CharactersApi
import com.marvel.characters.frameworks.remote.CharactersRemoteDataSource
import com.marvel.characters.frameworks.services.ServiceClient
import com.marvel.characters.presentation.utils.getCurrentTimeStamp
import com.marvel.characters.presentation.utils.toMd5
import com.marvel.characters.presentation.viewmodels.CharacterDetailsViewModel
import com.marvel.characters.presentation.viewmodels.CharactersListViewModel
import com.marvel.core.characters.data.contracts.CharactersDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val characterViewModelModule = module {
    single { ServiceClient.getClient(api = CharactersApi::class.java) }

    val timeStamp = getCurrentTimeStamp()

    factory<CharactersDataSource> {
        CharactersRemoteDataSource(
            api = get(),
            apiKey = BuildConfig.PUBLIC_KEY,
            timestamp = timeStamp,
            hash = "$timeStamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}".toMd5()
        )
    }

    viewModel {
        CharactersListViewModel(
            fetchAllCharacters = get(),
            fetchAllCharactersByName = get()
        )
    }
    viewModel { CharacterDetailsViewModel(get()) }
}

fun getAppModules() = listOf(characterViewModelModule)