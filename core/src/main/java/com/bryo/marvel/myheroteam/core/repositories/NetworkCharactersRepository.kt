package com.bryo.marvel.myheroteam.core.repositories

import com.bryo.marvel.myheroteam.core.mappers.Mapper
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.core.network.MarvelApiService
import com.bryo.marvel.myheroteam.core.network.models.ApiCharacter

class NetworkCharactersRepository(private val apiService: MarvelApiService,
                                  private val charactersMapper: Mapper<ApiCharacter, MarvelCharacter>) : CharactersRepository {

    override suspend fun getCharacters(forceRefresh: Boolean): List<MarvelCharacter> {
        return apiService
            .getMarvelCharacters()
            .data
            .results
            .map { charactersMapper.map(it) }
    }
}