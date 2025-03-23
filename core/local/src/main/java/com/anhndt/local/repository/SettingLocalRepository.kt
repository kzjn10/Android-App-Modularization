package com.anhndt.local.repository

import com.anhndt.model.AppConfig
import kotlinx.coroutines.flow.Flow

interface SettingLocalRepository {
    suspend fun toggleThemeMode()

    fun isDarkMode(): Flow<Boolean>

    suspend fun toggleQuoteStyle()

    fun isCardStyle(): Flow<Boolean>

    suspend fun updateLanguage(language: String)

    fun getLanguage(): Flow<String>

    fun getAppConfig(): Flow<AppConfig>

    suspend fun clearCache()
}
