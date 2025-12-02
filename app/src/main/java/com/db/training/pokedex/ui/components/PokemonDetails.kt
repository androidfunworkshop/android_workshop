package com.db.training.pokedex.ui.components


import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Copyright © 2025. All rights reserved.
 **/
@Composable
fun PokemonDetails(onBackPressed: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Icon(
            modifier = Modifier
                .padding(6.dp)
                .clickable { onBackPressed() },
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            tint = Color.Black,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(24.dp))
        Text("Press X to go back", color = Color.Black)
    }

}