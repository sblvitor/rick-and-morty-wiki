package com.lira.rickandmortywiki.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lira.rickandmortywiki.data.model.character.CharacterList
import com.lira.rickandmortywiki.domain.ListAllCharactersUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(private val listAllCharactersUseCase: ListAllCharactersUseCase) : ViewModel() {

    private val _characters = MutableLiveData<State>()
    val characters: LiveData<State> = _characters

    fun getAllCharactersList(){
        viewModelScope.launch {
            listAllCharactersUseCase()
                .onStart {
                    _characters.postValue(State.Loading)
                }
                .catch {
                    _characters.postValue(State.Error(it))
                }
                .collect {
                    _characters.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading: State()
        data class Success(val list: Response<CharacterList>): State()
        data class Error(val error: Throwable): State()
    }
}