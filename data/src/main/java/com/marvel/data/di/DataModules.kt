package com.marvel.data.di

import com.marvel.data.CharacterRepositoryImpl
import com.marvel.data.RetrofitInitializer
import com.marvel.data.contracts.CharacterDataSource
import com.marvel.data.contracts.CharacterRepository
import com.marvel.data.remote.datasource.CharacterRemoteDataSource
import org.koin.dsl.module

private val apiServiceModule = module {
    single { RetrofitInitializer().apiService() }
}

private val dataSourceModule = module {
    single<CharacterDataSource.Remote> { CharacterRemoteDataSource(get()) }
}

private val repositoryModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
}

fun getDataModules() = listOf(apiServiceModule, repositoryModule, dataSourceModule)