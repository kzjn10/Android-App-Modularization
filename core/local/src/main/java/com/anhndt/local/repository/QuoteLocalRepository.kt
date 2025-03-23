package com.anhndt.local.repository

import com.anhndt.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteLocalRepository {
    suspend fun getAllQuotes(): Flow<List<Quote>?>

    suspend fun saveQuotes(quotes: List<Quote>)

    suspend fun clearQuotes()

    suspend fun clearCache()
}