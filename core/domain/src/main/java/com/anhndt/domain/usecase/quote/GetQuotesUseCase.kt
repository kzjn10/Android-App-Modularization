package com.anhndt.domain.usecase.quote

import com.anhndt.model.Quote

interface GetQuotesUseCase {
    suspend fun getQuotes(isForceRefresh: Boolean): Result<List<Quote>?>
}