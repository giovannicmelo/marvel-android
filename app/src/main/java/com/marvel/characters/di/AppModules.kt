package com.marvel.characters.di

import com.marvel.characters.ui.character.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val characterViewModelModule = module {
    viewModel { CharacterViewModel(get()) }
}

fun getAppModules() = listOf(characterViewModelModule)