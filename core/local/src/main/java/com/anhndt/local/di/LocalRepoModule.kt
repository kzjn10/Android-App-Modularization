package com.anhndt.local.di

import android.content.Context
import com.anhndt.local.dao.QuoteDAO
import com.anhndt.local.repository.QuoteLocalRepository
import com.anhndt.local.repository.SettingLocalRepository
import com.anhndt.local.repository.impl.QuoteLocalRepositoryImpl
import com.anhndt.local.repository.impl.SettingLocalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalRepoModule {
    @Provides
    @Singleton
    fun provideQuoteLocalRepository(quoteDAO: QuoteDAO): QuoteLocalRepository {
        return QuoteLocalRepositoryImpl(quoteDAO)
    }

    @Provides
    @Singleton
    fun provideSettingLocalRepository(@ApplicationContext context: Context): SettingLocalRepository {
        return SettingLocalRepositoryImpl(context)
    }
}