package com.anhndt.network.api

import com.anhndt.model.response.QuoteResponse
import retrofit2.http.GET

interface QuoteApi {
    @GET("quotes")
    suspend fun getQuotes(): QuoteResponse?
}