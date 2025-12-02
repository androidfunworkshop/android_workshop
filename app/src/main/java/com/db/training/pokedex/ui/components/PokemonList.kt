package com.db.training.pokedex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.db.training.pokedex.R
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.ui.theme.PokedexTheme

/**
 * Copyright © 2025. All rights reserved.
 **/
//not scrollable
@Composable
fun PokemonList(pokemons: List<Pokemon>) {
    Column(modifier = Modifier.fillMaxSize()) {
        pokemons.forEach { pokemon ->
            PokemonItem(pokemon)
        }
    }
}

//scrollable
@Composable
fun PokemonLazyList(pokemons: List<Pokemon>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(pokemons) {
            PokemonItem(it)
        }
    }
}

//scrollable grid
@Composable
fun PokemonLazyGrid(pokemons: List<Pokemon>, onPokemonClicked: (Pokemon) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(6.dp),
    ) {
        items(pokemons) {
            PokemonItem(it, onPokemonClicked = { onPokemonClicked(it) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonLazyListPreview() {
    val pokemonMockList = listOf(
        Pokemon("Bulbasaur", R.drawable.bulbasaur),
        Pokemon("Bulbasaur", R.drawable.bulbasaur),
        Pokemon("Bulbasaur", R.drawable.bulbasaur),
        Pokemon("Bulbasaur", R.drawable.bulbasaur),
        Pokemon("Bulbasaur", R.drawable.bulbasaur),
        Pokemon("Bulbasaur", R.drawable.bulbasaur),
        Pokemon("Bulbasaur", R.drawable.bulbasaur),
        Pokemon("Bulbasaur", R.drawable.bulbasaur),

        )
    PokedexTheme {
        PokemonLazyGrid(pokemonMockList, {})
    }
}