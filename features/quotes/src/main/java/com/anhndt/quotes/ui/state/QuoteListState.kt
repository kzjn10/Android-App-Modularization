package com.anhndt.quotes.ui.state

import com.anhndt.model.Quote

sealed class QuoteListState {
    data object Loading : QuoteListState()
    data class Success(val quotes: List<Quote>) : QuoteListState()
    data class Error(val message: String) : QuoteListState()

}