package com.db.training.pokedex.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.db.training.pokedex.data.PokemonRepositoryImpl
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.domain.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonViewModel: ViewModel() {

    val repository: PokemonRepository = PokemonRepositoryImpl()

    // internal state
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(listOf())

    // public state, visible outside
    val pokemonList = _pokemonList.asStateFlow()

    init {
        _pokemonList.value = repository.getPokemon()
    }
}