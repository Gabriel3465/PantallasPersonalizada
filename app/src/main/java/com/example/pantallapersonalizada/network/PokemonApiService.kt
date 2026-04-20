package com.example.pantallapersonalizada.network

import com.example.pantallapersonalizada.models.PokemonSimple
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): PokemonSimple
}