package com.anhndt.quote.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import com.anhndt.common.constants.Destination
import com.anhndt.common.constants.NavRoute
import com.anhndt.favorite.navigation.favoriteScreen
import com.anhndt.profile.navigation.profileScreen
import com.anhndt.quotedetail.navigation.quoteDetailScreen
import com.anhndt.quotes.navigation.quotesScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = NavRoute.HomeRoute.route,
        modifier = modifier,
    ) {
        navigation(
            startDestination = Destination.Quotes.path,
            route = NavRoute.HomeRoute.route
        ) {
            quotesScreen(navController = navHostController)

            favoriteScreen(navController = navHostController)

            profileScreen(
                navController = navHostController,
            )
        }

        navigation(
            startDestination = Destination.QuoteDetail.path,
            route = NavRoute.DetailRoute.route
        ) {
            quoteDetailScreen(navController = navHostController)

        }

    }

}