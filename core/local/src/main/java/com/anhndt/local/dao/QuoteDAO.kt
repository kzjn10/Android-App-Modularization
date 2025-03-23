package com.anhndt.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.anhndt.local.model.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDAO {

    @Query("SELECT * FROM quote")
    fun getQuotes(): Flow<List<QuoteEntity>?>

    @Upsert
    fun insertQuote(vararg quote: QuoteEntity)

    @Query("DELETE FROM quote")
    fun deleteAllQuotes()
}