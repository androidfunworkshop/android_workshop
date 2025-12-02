package com.db.training.pokedex.data

import com.db.training.pokedex.R
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.domain.PokemonRepository

/**
 * Copyright © 2025. All rights reserved.
 **/
class PokemonRepositoryImpl : PokemonRepository {
    override fun getPokemon(): List<Pokemon> {
        return generatePokemonList()
    }
}

fun generatePokemonList(): List<Pokemon> {
    val pokemonList = mutableListOf<Pokemon>()
    pokemonList.addAll(
        listOf(
            Pokemon("Bulbasaur", R.drawable.bulbasaur, type = "grass"),
            Pokemon("Ivysaur", R.drawable.ivysaur, type = "grass"),
            Pokemon("Venusaur", R.drawable.venusaur, type = "grass"),
            Pokemon("Charmander", R.drawable.charmander, type = "fire"),
            Pokemon("Charmeleon", R.drawable.charmeleon, type = "fire"),
            Pokemon("Charizard", R.drawable.charizard, type = "fire"),
            Pokemon("Squirtle", R.drawable.squirtle, type = "water"),
            Pokemon("Wartortle", R.drawable.wartortle, type = "water"),
            Pokemon("Blastoise", R.drawable.blastoise, type = "water"),
            Pokemon("Caterpie", R.drawable.caterpie),
            Pokemon("Metapod", R.drawable.metapod),
            Pokemon("Butterfree", R.drawable.butterfree),
            Pokemon("Weedle", R.drawable.weedle),
            Pokemon("Kakuna", R.drawable.kakuna),
            Pokemon("Beedrill", R.drawable.beedrill),
            Pokemon("Pidgey", R.drawable.pidgey),
            Pokemon("Pidgeotto", R.drawable.pidgeotto),
            Pokemon("Pidgeot", R.drawable.pidgeot),
            Pokemon("Rattata", R.drawable.rattata),
            Pokemon("Raticate", R.drawable.raticate),
            Pokemon("Spearow", R.drawable.spearow),
            Pokemon("Fearow", R.drawable.fearow),
            Pokemon("Ekans", R.drawable.ekans),
            Pokemon("Arbok", R.drawable.arbok),
            Pokemon("Pikachu", R.drawable.pikachu),
            Pokemon("Raichu", R.drawable.raichu),
        )
    )
    return pokemonList
}