package com.db.training.pokedex.domain

import androidx.annotation.DrawableRes

/**
 * Copyright © 2025. All rights reserved.
 **/
data class Pokemon(
    val name: String,
    @DrawableRes val image: Int // @DrawableRes resource which can be rendered
)
