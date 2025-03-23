package com.anhndt.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anhndt.common.constants.Destination

fun NavGraphBuilder.favoriteScreen(navController: NavController) {
    composable(route = Destination.Favorite.path) {
        FavoriteRoute(navController = navController)
    }
}