package com.bryo.marvel.myheroteam.usecases

import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.core.repositories.CharactersRepository

class GetTeamMembers(private val charactersRepository: CharactersRepository): UseCase<Nothing?, List<MarvelCharacter>> {

    override suspend fun defer(arg: Nothing?): List<MarvelCharacter> {
        return charactersRepository.getTeamMembers()
    }
}