package com.bryo.marvel.myheroteam.di

import com.bryo.marvel.myheroteam.ui.detail.CharacterDetailViewModel
import com.bryo.marvel.myheroteam.ui.list.CharacterListViewModel
import com.bryo.marvel.myheroteam.usecases.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterListViewModel(get<GetTeamMembers>(), get<GetMarvelCharacters>()) }
    viewModel { CharacterDetailViewModel(get<GetMarvelCharacter>(), get<HireCharacter>(), get<FireCharacter>()) }
}