package com.anhndt.domain.usecase.quote.impl

import android.util.Log
import com.anhndt.domain.usecase.quote.GetQuotesUseCase
import com.anhndt.local.repository.QuoteLocalRepository
import com.anhndt.model.Quote
import com.anhndt.remote.quote.QuoteRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetQuotesUseCaseImpl @Inject constructor(
    private val quoteRepository: QuoteRepository,
    private val quoteLocalRepository: QuoteLocalRepository,
) : GetQuotesUseCase {
    override suspend fun getQuotes(isForceRefresh: Boolean): Result<List<Quote>?> {
        try {
            // Force fetch data from remote
            if (isForceRefresh) {
                Log.d("QuoteUseCase", "4. Get quotes from remote")
                val res = quoteRepository.getQuotes()
                // Upsert local data
                if (res.isNotEmpty()) {
                    Log.d("QuoteUseCase", "5. Set quotes to local")
                    quoteLocalRepository.saveQuotes(res)
                }

                return Result.success(res)
            } else {
                Log.d("QuoteUseCase", "1. Get quotes from local")
                val localData = quoteLocalRepository.getAllQuotes()
                // Fetch from remote if local data is empty
                if (localData.first()?.isNotEmpty() == true) {
                    return Result.success(localData.first())
                } else {
                    Log.d("QuoteUseCase", "2. Get quotes from remote")
                    val res = quoteRepository.getQuotes()
                    // Override local data
                    if (res.isNotEmpty()) {
                        Log.d("QuoteUseCase", "3. Save quotes to local")
                        quoteLocalRepository.saveQuotes(res)
                    }
                    return Result.success(res)
                }
            }
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}