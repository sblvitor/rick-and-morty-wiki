package com.lira.rickandmortywiki.domain.di

import com.lira.rickandmortywiki.domain.ListAllCharactersUseCase
import com.lira.rickandmortywiki.domain.ListCharactersByNameUseCase
import com.lira.rickandmortywiki.domain.ListCharactersFromNextPageUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load(){
        loadKoinModules(useCaseModule())
    }

    // Todos os useCases vem aqui
    private fun useCaseModule(): Module {
        return module {
            factory { ListAllCharactersUseCase(get()) }
            factory { ListCharactersByNameUseCase(get()) }
            factory { ListCharactersFromNextPageUseCase(get()) }
        }
    }

}