package com.lira.rickandmortywiki.data.repositories

import com.lira.rickandmortywiki.data.model.character.CharacterList
import com.lira.rickandmortywiki.data.services.RickAndMortyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class CharacterRepositoryImpl(private val service: RickAndMortyService): CharacterRepository {

    override suspend fun listCharacters() = flow {
        val charactersList = service.listCharacters()
        emit(charactersList)
    }

    override suspend fun listCharacterByName(name: String) = flow {
        val characterList = service.listCharacterByName(name)
        emit(characterList)
    }

}