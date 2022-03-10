package com.bryo.marvel.myheroteam.usecases

interface UseCase<I, O> : BaseUseCase<I, O> {
    suspend fun defer(arg: I): O

    companion object {
        suspend fun <E> UseCase<Nothing?, E>.await() = defer(null)
    }
}