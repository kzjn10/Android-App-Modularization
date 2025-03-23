package com.anhndt.remote.quote

import com.anhndt.model.Quote

interface QuoteRepository {
    suspend fun getQuotes(): List<Quote>

}