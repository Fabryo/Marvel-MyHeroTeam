package com.bryo.marvel.myheroteam.usecases

interface UseCase<I, O> : BaseUseCase<I, O> {
    suspend fun defer(arg: I): O
}