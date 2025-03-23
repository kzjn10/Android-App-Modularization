package com.anhndt.remote.di

import com.anhndt.network.api.QuoteApi
import com.anhndt.remote.quote.QuoteRepository
import com.anhndt.remote.quote.impl.QuoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideQuoteRepository(quoteApi: QuoteApi): QuoteRepository {
        return QuoteRepositoryImpl(quoteApi)
    }
}
