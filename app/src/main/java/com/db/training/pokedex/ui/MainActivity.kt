package com.db.training.pokedex.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import com.db.training.pokedex.R
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.ui.theme.PokedexTheme


class MainActivity : ComponentActivity() {

    val viewModel = PokemonViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _root_ide_package_.com.db.training.pokedex.ui.theme.PokedexTheme {

                val pokemonList by viewModel.pokemnonList.collectAsState()

                pokemonList.forEach{ pokemon -> Log.d("Pokemon:", pokemon.name)}

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        //PokemonDetails (Pokemon( name ="Pikachu", image=R.drawable.pikachu))
                        //PokemonDetails(pokemonList.random())
                        //Lazy


                      /*  Column(Modifier.padding(innerPadding).fillMaxSize() .verticalScroll(
                            rememberScrollState()
                        )) {
                            pokemonList.forEach { p-> PokemonDetails(p)  }

                        }*/
                        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                            items(pokemonList) {
                                PokemonDetails(it)
                            }
                        }

                        //LazyColumn() {
                          //  items(pokemonList§)
                       // }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, device=PIXEL_4, showSystemUi = true)
@Composable
fun GreetingPreview() {
    _root_ide_package_.com.db.training.pokedex.ui.theme.PokedexTheme {
        PokemonPreview()
    }
}


@Composable
fun PokemonDetails( pokemon: Pokemon) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(pokemon.image),
            contentDescription = pokemon.name,
        )
        Text(pokemon.name)
    }
}


@Composable
fun PokemonPreview() {
    PokedexTheme {
      PokemonDetails ((Pokemon( name ="Pikachu", image=R.drawable.pikachu)  ))

  }

}



