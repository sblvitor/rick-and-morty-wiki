package com.lira.rickandmortywiki.domain

import com.lira.rickandmortywiki.core.UseCase
import com.lira.rickandmortywiki.data.model.character.CharacterList
import com.lira.rickandmortywiki.data.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ListAllCharactersUseCase(private val repository: CharacterRepository): UseCase.NoParam<Response<CharacterList>>() {

    override suspend fun execute(): Flow<Response<CharacterList>> {
        return repository.listCharacters()
    }
}