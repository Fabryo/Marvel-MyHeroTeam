package com.bryo.marvel.myheroteam.core.repositories

import com.bryo.marvel.myheroteam.core.database.DatabaseRepository
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import org.koin.core.KoinComponent
import org.koin.core.get
import java.util.*

class UsingDatabaseCharactersRepository(private val innerRepository: CharactersRepository,
                                        private val databaseRepository: DatabaseRepository) : CharactersRepository {

    override suspend fun getCharacters(forceRefresh: Boolean): List<MarvelCharacter> {
        return if (!forceRefresh && databaseRepository.getLastFetchDate()?.isYoungerThan24h() == true) {
            databaseRepository.getAllCharacters()
        } else {
            innerRepository.getCharacters(forceRefresh)
                .also {
                    databaseRepository.setLastFetchDate(now())
                    databaseRepository.insertAllCharacters(it)
                }
        }
    }

    override suspend fun getCharacter(id: Int): MarvelCharacter {
        return databaseRepository.getCharacter(id) ?: innerRepository.getCharacter(id)
    }

    override suspend fun getTeamMembers(): List<MarvelCharacter> {
        return databaseRepository.getHiredCharacters()
    }

    private fun now() = Calendar.getInstance().time

    private fun Date.isYoungerThan24h(): Boolean {
        val oneDayInMillis = 24 * 60 * 60 * 1000
        return now().time - time < oneDayInMillis
    }

    companion object: KoinComponent {
        fun CharactersRepository.useDatabase() = UsingDatabaseCharactersRepository(this, get())
    }
}