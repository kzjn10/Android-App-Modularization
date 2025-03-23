package com.anhndt.quotes.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anhndt.common.constants.Destination
import com.anhndt.quotes.ui.view.QuotesScreen
import com.anhndt.quotes.ui.viewmodel.QuotesViewModel

@Composable
fun QuotesRoute(
    navController: NavController,
    viewModel: QuotesViewModel = hiltViewModel(),
) {
    QuotesScreen(
        viewModel = viewModel,
        onNavigateToDetail = {
            navController.navigate(Destination.QuoteDetail.withArgs(it.author, it.quote)) {
            }
        })

}