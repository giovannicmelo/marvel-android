package com.marvel.characters.di

import com.marvel.characters.BuildConfig
import com.marvel.characters.frameworks.local.CharactersLocalDataSource
import com.marvel.characters.frameworks.local.dao.CharactersDao
import com.marvel.characters.frameworks.remote.CharactersRemoteDataSource
import com.marvel.characters.frameworks.remote.api.CharactersApi
import com.marvel.characters.frameworks.remote.services.ServiceClient
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

    factory<CharactersDataSource.Remote> {
        CharactersRemoteDataSource(
            api = get(),
            apiKey = BuildConfig.PUBLIC_KEY,
            timestamp = timeStamp,
            hash = "$timeStamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}".toMd5()
        )
    }

    factory<CharactersDataSource.Local> {
        CharactersLocalDataSource(
            dao = CharactersDao
        )
    }

    viewModel {
        CharactersListViewModel(
            fetchCharactersList = get(),
            fetchCharactersByName = get()
        )
    }
    viewModel { CharacterDetailsViewModel(get()) }
}

fun getAppModules() = listOf(characterViewModelModule)