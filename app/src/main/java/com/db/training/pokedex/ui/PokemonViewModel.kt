package com.db.training.pokedex.ui

import android.app.Application
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.db.training.pokedex.data.PokemonRepositoryImpl
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.domain.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Copyright © 2025. All rights reserved.
 **/
class PokemonViewModel(application: Application) : AndroidViewModel(application) {

    val repository: PokemonRepository = PokemonRepositoryImpl()

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(listOf())
    val pokemonList = _pokemonList.asStateFlow()

    init {
        val initialList = repository.getPokemon()
        _pokemonList.value = initialList
        viewModelScope.launch {
            val modifiedList = initialList.toMutableList()
            initialList.forEachIndexed { index, pokemon ->
                val updatedPokemon = pokemon.copy(backgroundColor = getDominantColor(pokemon))
                modifiedList[index] = updatedPokemon
                delay(200L)
                _pokemonList.value =  modifiedList.toList()
            }
        }
    }

    private suspend fun getDominantColor(pokemon: Pokemon): Color = withContext(Dispatchers.IO) {
        val drawable = ContextCompat.getDrawable(getApplication(), pokemon.image) as? BitmapDrawable ?: return@withContext Color.Gray

        val palette = Palette.from(drawable.bitmap).generate()
        val dominantColor = palette.getDominantColor(0xFFCCCCCC.toInt())
        Color(dominantColor)
    }

}