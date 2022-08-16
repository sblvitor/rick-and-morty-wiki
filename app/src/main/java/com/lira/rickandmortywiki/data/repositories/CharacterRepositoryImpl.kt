package com.lira.rickandmortywiki.data.repositories

import com.lira.rickandmortywiki.data.services.RickAndMortyService
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(private val service: RickAndMortyService): CharacterRepository {

    override suspend fun listCharacters() = flow {
        val charactersList = service.listCharacters()
        emit(charactersList)
    }

}