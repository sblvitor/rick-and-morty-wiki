package com.lira.rickandmortywiki.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.lira.rickandmortywiki.data.repositories.CharacterRepository
import com.lira.rickandmortywiki.data.repositories.CharacterRepositoryImpl
import com.lira.rickandmortywiki.data.services.RickAndMortyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    private const val OK_HTTP = "OkHttp"

    fun load(){
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module{
        return module {
            // single = Sempre a mesma instância quando for pedido
            // Instância do HttpClient pro Koin prover
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.d(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            // Instância do GsonConverterFactory
            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<RickAndMortyService>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<CharacterRepository> { CharacterRepositoryImpl(get()) }
        }
    }

    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }

}