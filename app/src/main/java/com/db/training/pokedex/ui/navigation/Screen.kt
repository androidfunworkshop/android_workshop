package com.db.training.pokedex.ui.navigation

/**
 * Copyright © 2025. All rights reserved.
 **/
sealed class Screen(val route: String) {
    object List : Screen("list")
    object Details : Screen("details")
}