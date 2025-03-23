package com.anhndt.model.response

import com.anhndt.model.Quote

data class QuoteResponse(
    val quotes: List<Quote>?
)