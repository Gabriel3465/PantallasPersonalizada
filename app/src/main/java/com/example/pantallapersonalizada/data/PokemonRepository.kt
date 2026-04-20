package com.example.pantallapersonalizada.data

import com.example.pantallapersonalizada.models.PokemonSimple
import com.example.pantallapersonalizada.network.RetrofitInstance

class PokemonRepository {
    suspend fun getPokemon(name: String): PokemonSimple {
        return RetrofitInstance.api.getPokemon(name)
    }
}