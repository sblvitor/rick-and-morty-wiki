package com.lira.rickandmortywiki.data.repositories

import com.lira.rickandmortywiki.data.model.character.CharacterList
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CharacterRepository {
    suspend fun listCharacters(): Flow<Response<CharacterList>>
}