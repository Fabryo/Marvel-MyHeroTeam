package com.bryo.marvel.myheroteam.core.database

import com.bryo.marvel.myheroteam.core.database.characters.CharacterBase
import com.bryo.marvel.myheroteam.core.database.characters.CharactersDao
import com.bryo.marvel.myheroteam.core.database.fetch.FetchDao
import com.bryo.marvel.myheroteam.core.database.fetch.FetchServer
import com.bryo.marvel.myheroteam.core.mappers.Mapper
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import java.util.*

class DatabaseRepository(
    private val charactersDao: CharactersDao,
    private val fetchDao: FetchDao,
    private val marvelCharacterToDatabaseCharacterMapper: Mapper<MarvelCharacter, CharacterBase>,
    private val databaseCharacterToMarvelCharacterMapper: Mapper<CharacterBase, MarvelCharacter>
) {

    fun getAllCharacters(): List<MarvelCharacter> = charactersDao.getAllCharacters()?.map {
        databaseCharacterToMarvelCharacterMapper.map(it)
    } ?: emptyList()

    fun insertAllCharacters(marvelCharacters: List<MarvelCharacter>) {
        return charactersDao.insertAllCharacters(marvelCharacters.map {
            marvelCharacterToDatabaseCharacterMapper.map(it)
        })
    }

    fun insert(marvelCharacter: MarvelCharacter) {
        return charactersDao.insert(marvelCharacterToDatabaseCharacterMapper.map(marvelCharacter))
    }

    fun deleteAllCharacters() = charactersDao.deleteAllCharacters()

    fun getCharacter(characterId: Int): MarvelCharacter? = charactersDao.getCharacter(characterId)
        ?.let { databaseCharacterToMarvelCharacterMapper.map(it) }

    fun deleteCharacter(characterId: Int) {
        return charactersDao.deleteCharacter(characterId)
    }

    fun getLastFetchDate(): Date? = fetchDao.getLastFetchDate()?.date
    fun setLastFetchDate(date: Date) = fetchDao.insertLastFetchDate(FetchServer(1, date))
}