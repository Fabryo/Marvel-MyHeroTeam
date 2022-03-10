package com.bryo.marvel.myheroteam.usecases

import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.core.repositories.CharactersRepository

class GetMarvelCharacters(private val charactersRepository: CharactersRepository): UseCase<Boolean, List<MarvelCharacter>> {

    override suspend fun defer(arg: Boolean): List<MarvelCharacter> {
        return charactersRepository.getCharacters(arg)
    }
}