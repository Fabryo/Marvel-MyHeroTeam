package com.bryo.marvel.myheroteam.di

import com.bryo.marvel.myheroteam.core.repositories.NetworkCharactersRepository
import com.bryo.marvel.myheroteam.core.repositories.UsingDatabaseCharactersRepository.Companion.useDatabase
import com.bryo.marvel.myheroteam.usecases.GetMarvelCharacters
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetMarvelCharacters(get<NetworkCharactersRepository>().useDatabase()) }
}
