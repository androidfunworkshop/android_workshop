package com.db.training.pokedex.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.db.training.pokedex.ui.components.PokemonDetails
import com.db.training.pokedex.ui.components.PokemonLazyGrid
import com.db.training.pokedex.ui.navigation.Screen
import com.db.training.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {

    val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                val pokemonList by viewModel.pokemonList.collectAsState()
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.List.route
                        ) {
                            composable(Screen.List.route) {
                                PokemonLazyGrid(
                                    pokemons = pokemonList,
                                    onPokemonClicked = {
                                        navController.navigate(Screen.Details.route)
                                    })
                            }
                            composable(Screen.Details.route) {
                                PokemonDetails(onBackPressed = {
                                    navController.popBackStack()
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}







