package com.bryo.marvel.myheroteam.core.repositories

import com.bryo.marvel.myheroteam.core.models.MarvelCharacter

interface CharactersRepository {
    suspend fun getCharacters(forceRefresh: Boolean): List<MarvelCharacter>
}