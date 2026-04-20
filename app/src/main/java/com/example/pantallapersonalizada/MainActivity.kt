package com.example.pantallapersonalizada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.pantallapersonalizada.navigation.AppNavigation
import com.example.pantallapersonalizada.screens.PokemonSimpleScreen
import com.example.pantallapersonalizada.ui.theme.PantallaPersonalizadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PantallaPersonalizadaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //AppNavigation(modifier = Modifier.fillMaxSize())
                    PokemonSimpleScreen()
                }
            }
        }
    }
}