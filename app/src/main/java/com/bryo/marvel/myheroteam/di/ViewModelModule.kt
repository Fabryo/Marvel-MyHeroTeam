package com.bryo.marvel.myheroteam.di

import com.bryo.marvel.myheroteam.usecases.GetMarvelCharacters
import com.bryo.marvel.myheroteam.ui.list.CharacterListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterListViewModel(get<GetMarvelCharacters>()) }
}