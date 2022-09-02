package com.lira.rickandmortywiki.data.repositories

import com.lira.rickandmortywiki.core.RemoteException
import com.lira.rickandmortywiki.data.model.character.CharacterList
import com.lira.rickandmortywiki.data.services.RickAndMortyService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response

class CharacterRepositoryImpl(private val service: RickAndMortyService): CharacterRepository {

    override suspend fun listCharacters() = flow {
        try {
            val charactersList = service.listCharacters()
            emit(charactersList)
        } catch (e: HttpException) {
            throw RemoteException(e.message ?: "Não foi possível obter os resultados!")
        }

    }

    override suspend fun listCharacterByName(name: String) = flow {
        try {
            val response = service.listCharacterByName(name)
            if(response.isSuccessful)
                emit(response)
            else {
                throw RemoteException("Nenhum personagem encontrado!")
            }
        } catch (e: HttpException) {
            throw RemoteException(e.message ?: "Não foi possível obter os resultados!")
        }
    }

    override suspend fun listCharactersFromNextPage(nextPageUrl: String) = flow {
        try {
            val response = service.listCharactersFromNextPage(nextPageUrl)
            if(response.isSuccessful)
                emit(response)
            else
                throw RemoteException("Não foi possível encontrar a próxima página!")
        } catch (e: HttpException) {
            throw RemoteException(e.message() ?: "Não foi possível obter os resultados!")
        }
    }

}