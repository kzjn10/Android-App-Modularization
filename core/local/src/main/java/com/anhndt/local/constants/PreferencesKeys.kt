package com.anhndt.local.constants

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    const val DATA_STORE_NAME = "settings"
    val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
    val IS_CARD_STYLE = booleanPreferencesKey("is_card_style")
    val LANGUAGE = stringPreferencesKey("language")
}