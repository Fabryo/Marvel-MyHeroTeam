package com.bryo.marvel.myheroteam.core.network

import com.bryo.marvel.myheroteam.core.network.models.ApiCharacter
import com.bryo.marvel.myheroteam.core.network.models.MarvelApiResponse
import retrofit2.http.GET

interface MarvelApiService {
    @GET(PATH_CHARACTERS)
    suspend fun getMarvelCharacters(): MarvelApiResponse<List<ApiCharacter>>

    companion object {
        private const val PATH_CHARACTERS = "/v1/public/characters"
    }
}