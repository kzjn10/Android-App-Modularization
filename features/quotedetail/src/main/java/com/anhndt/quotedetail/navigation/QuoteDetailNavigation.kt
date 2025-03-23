package com.anhndt.quotedetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.anhndt.common.constants.Destination

fun NavGraphBuilder.quoteDetailScreen(navController: NavController) {
    composable(
        route = Destination.QuoteDetail.withArgsFormat(
        Destination.QuoteDetail.author,
        Destination.QuoteDetail.quote,
    ),
        arguments = listOf(
            navArgument(Destination.QuoteDetail.author) {
                type = NavType.StringType
                nullable = false
            },
            navArgument(Destination.QuoteDetail.quote) {
                type = NavType.StringType
                nullable = false
            }
        ),
        deepLinks = listOf(navDeepLink {
            uriPattern =
                "quote_app://{${Destination.QuoteDetail.path}}/{${Destination.QuoteDetail.author}}/{${Destination.QuoteDetail.quote}}"
        })
    ) {
        val author = it.arguments?.getString(Destination.QuoteDetail.author)
        val quote = it.arguments?.getString(Destination.QuoteDetail.quote)
        QuoteDetailRoute(navController = navController, author = author, quote = quote)
    }
}