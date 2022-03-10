package com.bryo.marvel.myheroteam.usecases

import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.core.repositories.CharactersRepository

class GetMarvelCharacter(private val charactersRepository: CharactersRepository): UseCase<Int, MarvelCharacter> {

    override suspend fun defer(arg: Int): MarvelCharacter {
        return charactersRepository.getCharacter(arg)
    }
}