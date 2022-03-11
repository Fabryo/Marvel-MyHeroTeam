package com.bryo.marvel.myheroteam.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.ui.list.CharacterListViewModel
import com.bryo.marvel.myheroteam.usecases.UseCase
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@ExperimentalCoroutinesApi
class CharacterListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()

    private val getTeamMembers = mock<UseCase<Nothing?, List<MarvelCharacter>>> {
        onBlocking { defer(null) }.doReturn(TEAM_MEMBERS)
    }
    private val getMarvelCharacters = mock<UseCase<Boolean, List<MarvelCharacter>>> {
        onBlocking { defer(false) }.doReturn(ALL_CHARACTERS)
    }

    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setUp() {
        startKoin {}
        Dispatchers.setMain(dispatcher)
        viewModel = CharacterListViewModel(getTeamMembers, getMarvelCharacters)
    }

    @Test
    fun fetchTest() = runBlocking {
        // Before load
        var isLoading = viewModel.loading.value
        assertNotNull(isLoading)
        assertTrue(isLoading!!)

        var teamMembers = viewModel.teamMembers.value
        assertNull(teamMembers)

        var characters = viewModel.characters.value
        assertNull(characters)

        // Actual
        viewModel.fetchCharacters(false)

        // After Load
        characters = viewModel.characters.value
        assertNotNull(characters)
        assert(characters == ALL_CHARACTERS)

        teamMembers = viewModel.teamMembers.value
        assertNotNull(teamMembers)
        assert(teamMembers == TEAM_MEMBERS)

        isLoading = viewModel.loading.value
        assertNotNull(isLoading)
        assertFalse(isLoading!!)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }

    companion object {
        val TEAM_MEMBERS = listOf(
            MarvelCharacter(1, "Hulk", "Green beast", "wrongThumbnailPath", true),
            MarvelCharacter(
                2,
                "Captain America",
                "America's greatest Hero",
                "wrongThumbnailPath",
                true
            ),
            MarvelCharacter(3, "Iron Man", "World's biggest Ego", "wrongThumbnailPath", true)
        )

        val ALL_CHARACTERS = listOf(
            MarvelCharacter(1, "Hulk", "Green beast", "wrongThumbnailPath", true),
            MarvelCharacter(
                2,
                "Captain America",
                "America's greatest Hero",
                "wrongThumbnailPath",
                true
            ),
            MarvelCharacter(3, "Iron Man", "World's biggest Ego", "wrongThumbnailPath", true),
            MarvelCharacter(
                4,
                "Black Widow",
                "Russia's best assassin",
                "wrongThumbnailPath",
                false
            ),
            MarvelCharacter(
                5,
                "Thor",
                "God of Thunder (and beer)",
                "wrongThumbnailPath",
                false
            ),
            MarvelCharacter(
                6,
                "Hawkeye",
                "The only reasonable one here",
                "wrongThumbnailPath",
                false
            )
        )
    }

}