package com.marvel.characters.di

import com.marvel.characters.frameworks.api.CharactersApi
import com.marvel.characters.frameworks.remote.CharactersRemoteDataSource
import com.marvel.characters.frameworks.services.ServiceClient
import com.marvel.characters.presentation.viewmodels.CharactersListViewModel
import com.marvel.characters.ui.character.CharacterViewModel
import com.marvel.core.characters.data.contracts.CharactersDataSource
import com.marvel.data.BuildConfig
import com.marvel.data.utils.getCurrentTimeStamp
import com.marvel.data.utils.toMd5
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.security.PublicKey

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

    viewModel { CharacterViewModel(get()) }
    viewModel {
        CharactersListViewModel(
            fetchAllCharactersUseCase = get(),
            fetchAllCharactersByNameUseCase = get()
        )
    }
}

fun getAppModules() = listOf(characterViewModelModule)