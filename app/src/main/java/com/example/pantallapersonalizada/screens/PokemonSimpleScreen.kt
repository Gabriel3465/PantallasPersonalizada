package com.example.pantallapersonalizada.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pantallapersonalizada.viewmodel.PokemonViewModel

@Composable
fun PokemonSimpleScreen(
    pokemonName: String = "ditto",
    viewModel: PokemonViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchPokemon(pokemonName)
    }

    val pokemon by viewModel.pokemon.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            loading -> CircularProgressIndicator()
            error != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Error: $error", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.fetchPokemon(pokemonName) }) {
                        Text("Reintentar")
                    }
                }
            }
            pokemon != null -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("ID: ${pokemon!!.id}", style = MaterialTheme.typography.headlineSmall)
                    Text("Nombre: ${pokemon!!.name}", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}