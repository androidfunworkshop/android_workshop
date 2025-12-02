package com.db.training.pokedex.ui.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.db.training.pokedex.R
import com.db.training.pokedex.domain.Pokemon
import com.db.training.pokedex.domain.Stats

/**
 * Copyright © 2025. All rights reserved.
 **/
@Composable
fun PokemonDetails(pokemon: Pokemon, onBackPressed: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Header(pokemon, onBackPressed)
        Spacer(modifier = Modifier.height(20.dp))
        Stats(pokemon.stats, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun Header(pokemon: Pokemon, onBackPressed: () -> Unit) {
    val shape = RoundedCornerShape(
        bottomStart = 64.dp,
        bottomEnd = 64.dp
    ) // topStart = 0.dp, topEnd = 0.dp by default
    val context = LocalContext.current
    var dominantColor by remember { mutableStateOf(Color.LightGray) }

    LaunchedEffect(pokemon.image) {
        dominantColor = getDominantColor(context, pokemon.image)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shadow(elevation = 10.dp, shape = shape)
            .background(color = dominantColor, shape = shape),
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .clickable { onBackPressed() },
                painter = painterResource(id = R.drawable.ic_arrow),
                tint = Color.White,
                contentDescription = null,
            )

            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = pokemon.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        }

        Image(
            modifier = Modifier
                .padding(top = 12.dp)
                .align(Alignment.BottomCenter)
                .size(200.dp),
            painter = painterResource(pokemon.image),
            contentDescription = pokemon.name,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
private fun Stats(stats: Stats, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(12.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp, bottom = 16.dp),
            text = "Base stats",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Stat("HP", stats.hp, color = Color.Red)
        Spacer(modifier = Modifier.height(4.dp))
        Stat("Attack", stats.attack, color = Color.Blue)
        Spacer(modifier = Modifier.height(4.dp))
        Stat("Defense", stats.defense, color = Color.Green)
    }
}

@Composable
private fun Stat(name: String, value: Int, color: Color, modifier: Modifier = Modifier) {
    val currentProgress by remember { mutableFloatStateOf(value.toFloat() / 100) }
    Row(modifier = modifier) {
        Text(text = name, modifier = Modifier.width(80.dp))
        Spacer(modifier = Modifier.width(4.dp))
        LinearProgressIndicator(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
                .padding(end = 8.dp),
            trackColor = Color.Gray,
            color = color,
            progress = { currentProgress },
            strokeCap = StrokeCap.Round,
            gapSize = 0.dp,
            drawStopIndicator = {}

        )
        Text(text = value.toString())
    }
}