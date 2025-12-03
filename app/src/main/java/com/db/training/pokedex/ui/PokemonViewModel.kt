package com.db.training.pokedex.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.db.training.pokedex.data.PokemonRepositoryImpl
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.domain.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonViewModel : ViewModel() {
    val repository : PokemonRepository = PokemonRepositoryImpl()

    private val _pokemonList = MutableStateFlow<List<Pokemon>> (listOf())

    val pokemonList = _pokemonList.asStateFlow() //se modifica starea private -> se modifica si cea publica

    init { // se executa cand se lanseaza activitatea, cand se creaza viewmodel-ul
        Log.e("vm", "init")
        _pokemonList.value = repository.getPokemon()
    }

}