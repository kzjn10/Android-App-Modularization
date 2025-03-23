package com.anhndt.local.repository.impl

import com.anhndt.local.dao.QuoteDAO
import com.anhndt.local.mapper.toEntity
import com.anhndt.local.mapper.toModel
import com.anhndt.local.repository.QuoteLocalRepository
import com.anhndt.model.Quote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuoteLocalRepositoryImpl @Inject constructor(
    private val quote: QuoteDAO

) : QuoteLocalRepository {
    override suspend fun getAllQuotes(): Flow<List<Quote>?> {
        return quote.getQuotes().map { quoteList -> quoteList?.map { it.toModel() } }
    }

    override suspend fun saveQuotes(quotes: List<Quote>) {
        clearQuotes()
        quotes.forEach {
            quote.insertQuote(it.toEntity())
        }
    }

    override suspend fun clearCache() {
        clearQuotes()
    }

    override suspend fun clearQuotes() {
        quote.deleteAllQuotes()
    }
}