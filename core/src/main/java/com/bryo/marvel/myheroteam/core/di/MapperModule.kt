package com.bryo.marvel.myheroteam.core.di

import com.bryo.marvel.myheroteam.core.mappers.ApiCharacterToMarvelCharacterMapper
import com.bryo.marvel.myheroteam.core.mappers.DatabaseCharacterToMarvelCharacterMapper
import com.bryo.marvel.myheroteam.core.mappers.MarvelCharacterToDatabaseCharacterMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { ApiCharacterToMarvelCharacterMapper() }
    factory { MarvelCharacterToDatabaseCharacterMapper() }
    factory { DatabaseCharacterToMarvelCharacterMapper() }
}
