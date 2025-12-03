package com.db.training.pokedex.ui

import androidx.lifecycle.ViewModel
import com.db.training.pokedex.data.PokemonRepositoryImpl
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.domain.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonViewModel : ViewModel() {

    val repository : PokemonRepository = PokemonRepositoryImpl()

    private val _pokemnonList = MutableStateFlow<List<Pokemon>>(listOf())

    val pokemnonList = _pokemnonList.asStateFlow()

    init{

        _pokemnonList.value = repository.getPokemon()
    }
}