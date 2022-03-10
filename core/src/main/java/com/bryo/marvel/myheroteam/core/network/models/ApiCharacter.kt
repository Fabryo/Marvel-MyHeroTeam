package com.bryo.marvel.myheroteam.core.network.models

data class ApiCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

data class Thumbnail(val path: String, val extension: String)