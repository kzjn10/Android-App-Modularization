@file:OptIn(ExperimentalMaterial3Api::class)

package com.anhndt.quotes.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Reorder
import androidx.compose.material.icons.outlined.Square
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anhndt.designsystem.components.cardstack.CardStack
import com.anhndt.designsystem.components.errorcard.ErrorCard
import com.anhndt.designsystem.components.loadingcard.LoadingCard
import com.anhndt.designsystem.components.swipablecard.SwipeableCard
import com.anhndt.model.Quote
import com.anhndt.quotes.ui.state.QuoteListState
import com.anhndt.quotes.ui.view.components.QuoteItem
import com.anhndt.quotes.ui.view.components.QuoteLoadingItem
import com.anhndt.quotes.ui.viewmodel.QuotesViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuotesScreen(
    modifier: Modifier = Modifier,
    viewModel: QuotesViewModel,
    onNavigateToDetail: (Quote) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()
    val currentCardIndex = viewModel.currentCardIndex.collectAsState()
    val appConfig by viewModel.appConfig.collectAsStateWithLifecycle()

    QuoteLayout(
        modifier = modifier,
        state = uiState.value,
        listState = listState,
        onToggleUI = {
            viewModel.toggleUI()
        },
        onNavigateToDetail = onNavigateToDetail,
        onRefresh = {
            isRefreshing = true
            MainScope().launch {
                delay(500)
                viewModel.getQuotes(isForceRefresh = isRefreshing)
                isRefreshing = false
            }
        },
        isRefreshing = isRefreshing,
        pullToRefreshState = pullToRefreshState,
        isCardMode = appConfig?.isCardStyle ?: true,
        currentCardIndex = currentCardIndex.value,
        onSwipeRight = {
            viewModel.onSwipeCard(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteLayout(
    modifier: Modifier,
    state: QuoteListState,
    listState: LazyListState,
    onToggleUI: () -> Unit,
    onNavigateToDetail: (Quote) -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    pullToRefreshState: PullToRefreshState,
    onSwipeRight: (Boolean) -> Unit,
    isCardMode: Boolean = true,
    currentCardIndex: Int
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { },
                actions = {
                    IconButton(onClick = onToggleUI) {
                        Icon(
                            imageVector = if (isCardMode) Icons.Outlined.Reorder else Icons.Outlined.Square,
                            contentDescription = null
                        )
                    }
                }
            )
        },

        ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is QuoteListState.Loading -> {
                    LoadingUI(isCardMode = isCardMode)
                }

                is QuoteListState.Error -> {
                    ErrorCard(message = state.message)
                }

                is QuoteListState.Success -> {
                    if (isCardMode)
                        QuoteCardUI(
                            quotes = state.quotes,
                            currentCardIndex = currentCardIndex,
                            onSwipeRight = onSwipeRight
                        )
                    else
                        QuotesListUI(
                            listState = listState,
                            quotes = state.quotes,
                            onNavigateToDetail = onNavigateToDetail,
                            isRefreshing = isRefreshing,
                            onRefresh = onRefresh,
                            pullToRefreshState = pullToRefreshState
                        )
                }
            }
        }
    }

}

@Composable
fun LoadingUI(modifier: Modifier = Modifier, isCardMode: Boolean) {
    if (isCardMode)
        LoadingCard(modifier)
    else
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.TopStart
        ) {

            LazyColumn {
                items(20, key = { index -> index }) { _ ->
                    QuoteLoadingItem()
                }
            }
        }
}

@Composable
fun QuotesListUI(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    quotes: List<Quote>,
    onNavigateToDetail: (Quote) -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    pullToRefreshState: PullToRefreshState
) {
    PullToRefreshBox(
        modifier = modifier,
        state = pullToRefreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                state = pullToRefreshState
            )
        },
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(), state = listState
        ) {
            items(quotes.size, key = { index -> quotes[index].id }) {
                QuoteItem(
                    modifier = Modifier.animateItem(),
                    quote = quotes[it], onNavigateToDetail = onNavigateToDetail
                )
            }
        }
    }
}

@Composable
fun QuoteCardUI(
    modifier: Modifier = Modifier,
    quotes: List<Quote>,
    maxCard: Int = 5,
    onSwipeRight: (Boolean) -> Unit,
    currentCardIndex: Int
) {

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (quote, content) = createRefs()

        Text(
            modifier = Modifier.constrainAs(quote) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 32.dp)
                end.linkTo(parent.end)
            }.alpha(0.4f).fillMaxWidth(),
            text = stringResource(com.anhndt.l10n.R.string.app_name),
            style = MaterialTheme.typography.displayLarge
        )

        CardStack(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 64.dp)) {
            for (i in 0 until maxCard) {
                val index = (quotes.size + currentCardIndex % quotes.size + i) % quotes.size
                SwipeableCard(
                    modifier = Modifier.rotate((-4 + i) % maxCard * 1.0f),
                    index = index,
                    item = quotes[index],
                    onSwipeLeft = {
                        //Same logic for swipe left or right
                        onSwipeRight(false)
                    },
                    onSwipeRight = {
                        //Same logic for swipe left or right
                        onSwipeRight(false)
                    },
                )
            }
        }
    }


}



