package com.anhndt.domain.usecase.settings

interface SetConfigUseCase {
    suspend fun toggleTheme()
    suspend fun updateLanguage(language: String)
    suspend fun toggleCardStyle()
    suspend fun clearCache()
}