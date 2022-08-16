package com.lira.rickandmortywiki.data.services

import com.lira.rickandmortywiki.data.model.character.Character
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("/character")
    suspend fun listCharacters(): List<Character>

    @GET("/location")
    suspend fun listLocations()

    @GET("/episode")
    suspend fun listEpisodes()

}