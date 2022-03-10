package com.bryo.marvel.myheroteam.usecases

import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.core.repositories.CharactersRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetMarvelCharactersTest {

    private val charactersRepository = mock<CharactersRepository> {
        onBlocking { getCharacters(false) }.doReturn(AVENGERS)
    }

    private fun actual() = runBlocking {
        GetMarvelCharacters(charactersRepository).defer(false)
    }

    @Test
    fun testCharacterRepository() {
        assertEquals(actual(), AVENGERS)
    }

    companion object {
        private val HULK = MarvelCharacter(1, "Hulk", "Green beast", "wrongThumbnailPath", false)
        private val CAPTAIN_AMERICA =  MarvelCharacter(2, "Captain America", "America's greatest Hero", "wrongThumbnailPath", false)
        private val IRON_MAN = MarvelCharacter(3, "Iron Man", "World's biggest Ego", "wrongThumbnailPath", false)
        private val BLACK_WIDOW = MarvelCharacter(4, "Black Widow", "Russia's best assassin", "wrongThumbnailPath", false)
        private val THOR = MarvelCharacter(5, "Thor", "God of Thunder (and beer)", "wrongThumbnailPath", false)
        private val HAWKEYE = MarvelCharacter(6, "Hawkeye", "The only reasonable one here", "wrongThumbnailPath", false)

        val AVENGERS = listOf(
            HULK,
            CAPTAIN_AMERICA,
            IRON_MAN,
            BLACK_WIDOW,
            THOR,
            HAWKEYE
        )
    }
}