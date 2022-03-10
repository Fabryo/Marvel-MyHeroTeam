package com.bryo.marvel.myheroteam.core.providers

interface Provider<E> {
    fun get(): E
}