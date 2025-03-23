package com.anhndt.domain.di

import com.anhndt.domain.usecase.quote.GetQuotesUseCase
import com.anhndt.domain.usecase.quote.impl.GetQuotesUseCaseImpl
import com.anhndt.domain.usecase.settings.GetConfigUseCase
import com.anhndt.domain.usecase.settings.SetConfigUseCase
import com.anhndt.domain.usecase.settings.impl.GetConfigUseCaseImpl
import com.anhndt.domain.usecase.settings.impl.SetConfigUseCaseImpl
import com.anhndt.local.repository.QuoteLocalRepository
import com.anhndt.local.repository.SettingLocalRepository
import com.anhndt.remote.quote.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    @Singleton
    fun provideGetQuoteUseCase(
        quoteRepository: QuoteRepository,
        quoteLocalRepository: QuoteLocalRepository,
    ): GetQuotesUseCase {
        return GetQuotesUseCaseImpl(quoteRepository, quoteLocalRepository)
    }

    @Provides
    @Singleton
    fun provideGetConfigUseCase(
        settingLocalRepository: SettingLocalRepository
    ): GetConfigUseCase {
        return GetConfigUseCaseImpl(settingLocalRepository)
    }


    @Provides
    @Singleton
    fun provideSetConfigUseCase(
        settingLocalRepository: SettingLocalRepository,
        quoteLocalRepository: QuoteLocalRepository
    ): SetConfigUseCase {
        return SetConfigUseCaseImpl(settingLocalRepository, quoteLocalRepository)
    }
}