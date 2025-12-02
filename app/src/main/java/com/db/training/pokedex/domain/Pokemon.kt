package com.db.training.pokedex.domain

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

/**
 * Copyright © 2025. All rights reserved.
 **/
data class Pokemon(
    val name: String,
    @DrawableRes val image: Int,
    val backgroundColor: Color = Color.Gray,
    val stats: Stats = Stats.generateRandomStats(),
    val type: String = "normal"
)

data class Stats(
    var hp: Int = 0,
    var attack: Int = 0 ,
    var defense: Int = 0,
) {
    companion object {

    fun generateRandomStats() = Stats(
        hp = (0..100).random(),
        attack = (0..100).random(),
        defense = (0..100).random(),
        )
    }
}
