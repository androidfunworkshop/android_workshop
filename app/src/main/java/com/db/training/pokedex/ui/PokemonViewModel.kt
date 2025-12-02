package com.db.training.pokedex.ui

import androidx.lifecycle.ViewModel
import com.db.training.pokedex.data.PokemonRepositoryImpl
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.domain.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Copyright © 2025. All rights reserved.
 **/
class PokemonViewModel : ViewModel() {

    val repository: PokemonRepository = PokemonRepositoryImpl()

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(listOf())
    val pokemonList = _pokemonList.asStateFlow()

    init {
        _pokemonList.value = repository.getPokemon()
    }


}