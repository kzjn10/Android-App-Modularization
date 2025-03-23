package com.anhndt.local.repository.impl

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.anhndt.local.constants.PreferencesKeys
import com.anhndt.local.constants.PreferencesKeys.DATA_STORE_NAME
import com.anhndt.local.repository.SettingLocalRepository
import com.anhndt.model.AppConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingLocalRepositoryImpl @Inject constructor(private val context: Context) :
    SettingLocalRepository {

    private val Context.datastore by preferencesDataStore(name = DATA_STORE_NAME)

    override suspend fun toggleThemeMode() {
        context.datastore.edit { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] =
                !(preferences[PreferencesKeys.IS_DARK_MODE] ?: false)
        }
    }

    override fun isDarkMode(): Flow<Boolean> {
        return context.datastore.data.map { preferences ->
            preferences[PreferencesKeys.IS_DARK_MODE] ?: false
        }
    }

    override suspend fun toggleQuoteStyle() {
        context.datastore.edit { preferences ->
            preferences[PreferencesKeys.IS_CARD_STYLE] =
                !(preferences[PreferencesKeys.IS_CARD_STYLE] ?: false)
        }
    }

    override fun isCardStyle(): Flow<Boolean> {
        return context.datastore.data.map { preferences ->
            preferences[PreferencesKeys.IS_CARD_STYLE] ?: false
        }
    }

    override suspend fun updateLanguage(language: String) {
        context.datastore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE] = language
        }
    }

    override fun getLanguage(): Flow<String> {
        return context.datastore.data.map { preferences ->
            preferences[PreferencesKeys.LANGUAGE] ?: ""
        }
    }

    override fun getAppConfig(): Flow<AppConfig> {
        return context.datastore.data.map { preferences ->
            AppConfig(
                isDarkMode = preferences[PreferencesKeys.IS_DARK_MODE] ?: true,
                isCardStyle = preferences[PreferencesKeys.IS_CARD_STYLE] ?: true,
                language = preferences[PreferencesKeys.LANGUAGE] ?: ""
            )
        }
    }

    override suspend fun clearCache() {
        context.datastore.edit { preferences ->
            // Clear all preferences - without Language
            preferences[PreferencesKeys.IS_CARD_STYLE] = true
            preferences[PreferencesKeys.IS_DARK_MODE] = true

            // Clear all preferences
            // preferences.clear()
        }
    }
}