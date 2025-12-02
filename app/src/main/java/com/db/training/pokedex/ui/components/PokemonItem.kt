package com.db.training.pokedex.ui.components

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.db.training.pokedex.R
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Copyright © 2025. All rights reserved.
 **/
@Composable
fun PokemonItem(pokemon: Pokemon, onPokemonClicked: () -> Unit = {} ) {
    val dominantColor = pokemon.backgroundColor
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onPokemonClicked() },
        shape = RoundedCornerShape(size = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = dominantColor,
            contentColor = dominantColor,
            disabledContainerColor = dominantColor,
            disabledContentColor = dominantColor,
        ),

        ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp)
                .size(120.dp),
            painter = painterResource(pokemon.image),
            contentDescription = pokemon.name,
            contentScale = ContentScale.Fit,
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(12.dp),
            text = pokemon.name,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

private suspend fun getDominantColor(context: Context, @DrawableRes drawable: Int) =
    withContext(Dispatchers.IO) {
        val drawable = ContextCompat.getDrawable(context, drawable) ?: return@withContext Color.Gray

        val bitmap = (drawable as BitmapDrawable).bitmap

        val palette = Palette.from(bitmap).generate()

        val rgb = palette.getDominantColor(0xFFCCCCCC.toInt())
        Color(rgb)
    }

@Preview(showBackground = true)
@Composable
fun PokemonPreview() {
    PokedexTheme {
        PokemonItem((Pokemon("Bulbasaur", R.drawable.bulbasaur)))
    }
}