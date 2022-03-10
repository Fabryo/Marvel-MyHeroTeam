package com.bryo.marvel.myheroteam.usecases

import com.bryo.marvel.myheroteam.core.database.DatabaseRepository

class HireCharacter(private val databaseRepository: DatabaseRepository) : UseCase<Int, Unit> {

    override suspend fun defer(arg: Int) = databaseRepository.updateHiredCharacter(arg, true)
}