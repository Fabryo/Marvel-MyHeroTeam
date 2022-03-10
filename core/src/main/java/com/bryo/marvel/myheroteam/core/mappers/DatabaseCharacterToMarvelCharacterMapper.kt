package com.bryo.marvel.myheroteam.core.mappers

import com.bryo.marvel.myheroteam.core.database.characters.CharacterBase
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter

class DatabaseCharacterToMarvelCharacterMapper : Mapper<CharacterBase, MarvelCharacter> {
    override fun map(arg: CharacterBase) = MarvelCharacter(arg.id, arg.name, arg.description, arg.thumbnailPath, arg.hired)
}