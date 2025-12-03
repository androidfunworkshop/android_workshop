package com.db.training.pokedex.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.db.training.pokedex.data.PokemonRepositoryImpl
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {

    val viewModel = PokemonViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _root_ide_package_.com.db.training.pokedex.ui.theme.PokedexTheme {

                val pokemonList by viewModel.pokemonList.collectAsState()
                pokemonList.forEach { pokemon ->
                    Log.d("Pokemon", pokemon.name)
                }
                PokemonListScreen(pokemonList)
            }
        }
    }
}

@Composable
fun PokemonListScreen(pokemonList: List<Pokemon>) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                items(pokemonList) { pokemon ->
                    PokemonDetails(pokemon)
                }
            }
        }
    }
}

private fun getDominantColor(context: Context, pokemon: Pokemon): Color? {
    val drawable: BitmapDrawable? = ContextCompat.getDrawable(context,pokemon.image) as? BitmapDrawable
    if (drawable == null) {
        return Color.Magenta
    }

    val palette: Palette = Palette.from(drawable.bitmap).generate()
    val dominantColor = palette.getDominantColor(Color.Magenta.toArgb())
    return Color(dominantColor)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun PokemonDetails(pokemon: Pokemon) {
    Column(
        modifier = Modifier.background(
            color = getDominantColor(LocalContext.current, pokemon)!!,
            RoundedCornerShape(24.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = pokemon.name,
        )
        Text(pokemon.name)
    }
}

@Preview(device = PIXEL_4, showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    PokedexTheme {
        PokemonListScreen(PokemonRepositoryImpl().getPokemon())
    }
}
