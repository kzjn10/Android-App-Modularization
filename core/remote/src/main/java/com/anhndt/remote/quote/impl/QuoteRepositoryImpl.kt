package com.anhndt.remote.quote.impl

import com.anhndt.model.Quote
import com.anhndt.network.api.QuoteApi
import com.anhndt.remote.quote.QuoteRepository
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApi: QuoteApi
) : QuoteRepository {
    override suspend fun getQuotes(): List<Quote> {
        return quoteApi.getQuotes()?.quotes ?: emptyList()
    }
}