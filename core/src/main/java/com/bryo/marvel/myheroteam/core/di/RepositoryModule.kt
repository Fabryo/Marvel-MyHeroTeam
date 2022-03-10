package com.bryo.marvel.myheroteam.core.di

import com.bryo.marvel.myheroteam.core.database.DatabaseRepository
import com.bryo.marvel.myheroteam.core.mappers.ApiCharacterToMarvelCharacterMapper
import com.bryo.marvel.myheroteam.core.mappers.DatabaseCharacterToMarvelCharacterMapper
import com.bryo.marvel.myheroteam.core.mappers.MarvelCharacterToDatabaseCharacterMapper
import com.bryo.marvel.myheroteam.core.repositories.MockCharactersRepository
import com.bryo.marvel.myheroteam.core.repositories.NetworkCharactersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NetworkCharactersRepository(get(), get<ApiCharacterToMarvelCharacterMapper>()) }
    single { MockCharactersRepository() }
    single { DatabaseRepository(get(), get(), get<MarvelCharacterToDatabaseCharacterMapper>(), get<DatabaseCharacterToMarvelCharacterMapper>())}
}
