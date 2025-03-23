package com.anhndt.quotes.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anhndt.common.constants.Destination

fun NavGraphBuilder.quotesScreen(navController: NavController) {
    composable(route = Destination.Quotes.path) {
        QuotesRoute(navController = navController)
    }
}