package com.lira.rickandmortywiki.data.services

import com.lira.rickandmortywiki.data.model.character.CharacterList
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("character")
    suspend fun listCharacters(): Response<CharacterList>

    @GET("location")
    suspend fun listLocations()

    @GET("episode")
    suspend fun listEpisodes()

}