package com.anhndt.local.mapper

import com.anhndt.local.model.QuoteEntity
import com.anhndt.model.Quote

fun QuoteEntity.toModel() = Quote(
    id = id,
    author = author,
    quote = quote
)

fun Quote.toEntity() = QuoteEntity(
    id = id,
    author = author,
    quote = quote
)