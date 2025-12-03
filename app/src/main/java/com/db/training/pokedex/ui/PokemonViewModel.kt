package com.db.training.pokedex.ui

import androidx.lifecycle.ViewModel
import com.db.training.pokedex.data.PokemonRepositoryImpl
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.domain.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonViewModel: ViewModel() {
//    Dependency to repository
    val pokemonRepository: PokemonRepository = PokemonRepositoryImpl()

//    State management
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(listOf()) //listOf initializeaza o lista goala
    val pokemonList = _pokemonList.asStateFlow()

//    Init - se excecuta cand se initializeaza activitatea si ceere construirea viewModel
    init {
        _pokemonList.value = pokemonRepository.getPokemon()

    }

}