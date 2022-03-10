package com.bryo.marvel.myheroteam.core.mappers

import com.bryo.marvel.myheroteam.core.database.characters.CharacterBase
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter

class MarvelCharacterToDatabaseCharacterMapper: Mapper<MarvelCharacter, CharacterBase> {
    override fun map(arg: MarvelCharacter) = CharacterBase(arg.id, arg.name, arg.description, arg.thumbnailPath, arg.hired)
}