package com.example.pantallapersonalizada.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pantallapersonalizada.data.PokemonRepository
import com.example.pantallapersonalizada.models.PokemonSimple
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {
    private val repo = PokemonRepository()

    private val _pokemon = MutableStateFlow<PokemonSimple?>(null)
    val pokemon: StateFlow<PokemonSimple?> = _pokemon

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val result = repo.getPokemon(name)
                _pokemon.value = result
            } catch (e: Exception) {
                _error.value = e.message ?: "Error desconocido"
            } finally {
                _loading.value = false
            }
        }
    }
}