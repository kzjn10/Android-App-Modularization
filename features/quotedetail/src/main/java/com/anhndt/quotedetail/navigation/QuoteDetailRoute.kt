package com.anhndt.quotedetail.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anhndt.quotedetail.ui.view.QuoteDetailScreen
import com.anhndt.quotedetail.ui.viewmodel.QuoteDetailViewModel

@Composable
fun QuoteDetailRoute(
    navController: NavController,
    viewModel: QuoteDetailViewModel = hiltViewModel(),
    author: String?,
    quote: String?,
) {
    QuoteDetailScreen(
        navController = navController,
        viewModel = viewModel,
        author = author,
        quote = quote,
    )

}