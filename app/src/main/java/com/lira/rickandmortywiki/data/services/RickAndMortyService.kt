package com.lira.rickandmortywiki.data.services

import com.lira.rickandmortywiki.data.model.character.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyService {

    @GET("character")
    suspend fun listCharacters(): Response<CharacterList>

    @GET("character")
    suspend fun listCharacterByName(@Query("name") name: String): Response<CharacterList>

    @GET
    suspend fun listCharactersFromNextPage(@Url nextPageUrl: String): Response<CharacterList>

}