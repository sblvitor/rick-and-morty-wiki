package com.lira.rickandmortywiki.domain

import com.lira.rickandmortywiki.core.UseCase
import com.lira.rickandmortywiki.data.model.character.CharacterList
import com.lira.rickandmortywiki.data.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ListCharactersFromNextPageUseCase(private val repository: CharacterRepository): UseCase<String, Response<CharacterList>>() {

    override suspend fun execute(param: String): Flow<Response<CharacterList>> {
        return repository.listCharactersFromNextPage(param)
    }
}