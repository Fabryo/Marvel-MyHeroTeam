package com.bryo.marvel.myheroteam.core.mappers

import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.core.network.models.ApiCharacter
import com.bryo.marvel.myheroteam.core.network.models.Thumbnail
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.koin.core.KoinComponent

class ApiCharacterToMarvelCharacterMapperTest {

    private val mapper = ApiCharacterToMarvelCharacterMapper()

    @Test
    fun mapApiToMarvelCharacters() {
        assertThat(mapper.map(API_HULK), equalTo(UI_HULK))
    }

    private companion object : KoinComponent {
        val API_HULK = ApiCharacter(1234, "Hulk", "Green beast", Thumbnail("wrongThumbnailPath", "jpg"))
        val UI_HULK = MarvelCharacter(1234, "Hulk", "Green beast", "wrongThumbnailPath", false)
    }
}