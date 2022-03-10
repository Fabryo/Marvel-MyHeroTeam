package com.bryo.marvel.myheroteam.core.mappers

interface Mapper<I, O> {
    fun map(arg: I): O
}