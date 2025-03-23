package com.anhndt.domain.usecase.settings.impl

import com.anhndt.domain.usecase.settings.SetConfigUseCase
import com.anhndt.local.repository.QuoteLocalRepository
import com.anhndt.local.repository.SettingLocalRepository

class SetConfigUseCaseImpl(
    private val settingLocalRepository: SettingLocalRepository,
    private val quoteLocalRepository: QuoteLocalRepository
) : SetConfigUseCase {
    override suspend fun toggleTheme() {
        settingLocalRepository.toggleThemeMode()
    }

    override suspend fun updateLanguage(language: String) {
        settingLocalRepository.updateLanguage(language)
    }

    override suspend fun toggleCardStyle() {
        settingLocalRepository.toggleQuoteStyle()
    }

    override suspend fun clearCache() {
        settingLocalRepository.clearCache()
        quoteLocalRepository.clearCache()
    }
}