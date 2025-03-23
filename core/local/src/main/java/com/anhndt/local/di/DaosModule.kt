package com.anhndt.local.di

import com.anhndt.local.dao.QuoteDAO
import com.anhndt.local.dao.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    @Singleton
    fun providesQuoteDao(quoteDatabase: QuoteDatabase): QuoteDAO {
        return quoteDatabase.quoteDao()
    }
}