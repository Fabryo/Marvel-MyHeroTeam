package com.bryo.marvel.myheroteam.core.repositories

import com.bryo.marvel.myheroteam.core.models.MarvelCharacter

class MockCharactersRepository : CharactersRepository {

    override suspend fun getCharacters(forceRefresh: Boolean): List<MarvelCharacter> {
        return listOf(
            MarvelCharacter(1, "Hulk", "Green beast", "wrongThumbnailPath", true),
            MarvelCharacter(2, "Captain America", "America's greatest Hero", "wrongThumbnailPath", true),
            MarvelCharacter(3, "Iron Man", "World's biggest Ego", "wrongThumbnailPath", true),
            MarvelCharacter(4, "Black Widow", "Russia's best assassin", "wrongThumbnailPath", false),
            MarvelCharacter(5, "Thor", "God of Thunder (and beer)", "wrongThumbnailPath", false),
            MarvelCharacter(6, "Hawkeye", "The only reasonable one here", "wrongThumbnailPath", false)
        )
    }

    override suspend fun getCharacter(id: Int): MarvelCharacter {
        return MarvelCharacter(id, "Hulk", "Green beast", "wrongThumbnailPath", true)
    }

    override suspend fun getTeamMembers(): List<MarvelCharacter> {
        return listOf(
            MarvelCharacter(1, "Hulk", "Green beast", "wrongThumbnailPath", true),
            MarvelCharacter(2, "Captain America", "America's greatest Hero", "wrongThumbnailPath", true),
            MarvelCharacter(3, "Iron Man", "World's biggest Ego", "wrongThumbnailPath", true)
        )
    }
}