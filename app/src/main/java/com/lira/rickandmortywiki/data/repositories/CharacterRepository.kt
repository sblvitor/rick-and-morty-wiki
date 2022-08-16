package com.lira.rickandmortywiki.data.repositories

import com.lira.rickandmortywiki.data.model.character.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun listCharacters(): Flow<List<Character>>
}