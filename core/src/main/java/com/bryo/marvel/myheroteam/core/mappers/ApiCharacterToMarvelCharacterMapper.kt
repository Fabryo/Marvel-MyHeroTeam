package com.bryo.marvel.myheroteam.core.mappers

import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.core.network.models.ApiCharacter

class ApiCharacterToMarvelCharacterMapper: Mapper<ApiCharacter, MarvelCharacter> {
    override fun map(arg: ApiCharacter) = MarvelCharacter(arg.id, arg.name, arg.description, arg.thumbnail.path, false)
}