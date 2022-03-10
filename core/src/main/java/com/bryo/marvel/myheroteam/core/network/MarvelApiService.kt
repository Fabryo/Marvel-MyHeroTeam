package com.bryo.marvel.myheroteam.core.network

import com.bryo.marvel.myheroteam.core.network.models.ApiCharacter
import com.bryo.marvel.myheroteam.core.network.models.MarvelApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApiService {
    @GET(PATH_CHARACTERS)
    suspend fun getMarvelCharacters(): MarvelApiResponse<List<ApiCharacter>>

    @GET(PATH_CHARACTER)
    suspend fun getMarvelCharacter(@Path("characterId") characterId: Int): MarvelApiResponse<ApiCharacter>

    companion object {
        private const val PATH_CHARACTERS = "/v1/public/characters"
        private const val PATH_CHARACTER = "/v1/public/characters/{characterId}"
    }
}