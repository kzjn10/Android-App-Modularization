package com.anhndt.profile.ui.viewmodel

import android.app.LocaleManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anhndt.domain.usecase.settings.GetConfigUseCase
import com.anhndt.domain.usecase.settings.SetConfigUseCase
import com.anhndt.model.AppConfig
import com.anhndt.model.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getConfigUseCase: GetConfigUseCase,
    private val setConfigUseCase: SetConfigUseCase,
) : ViewModel(), DefaultLifecycleObserver {
    private val _buildVersion = MutableStateFlow("")
    val buildVersion: StateFlow<String> = _buildVersion

    private val _appConfig = MutableStateFlow<AppConfig?>(null)
    val appConfig: StateFlow<AppConfig?> = _appConfig

    private var clearCacheJob: Job? = null

    init {
        loadAppConfig()
    }

    fun getBuildVersion(context: Context) {
        try {
            val packageManager = context.packageManager
            val packageName = context.packageName
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
            } else {
                packageManager.getPackageInfo(packageName, 0)
            }

            _buildVersion.value = packageInfo.versionName ?: ""
        } catch (_: Exception) {
        }
    }

    private fun loadAppConfig() {
        viewModelScope.launch {
            getConfigUseCase.getAppConfig().collect {
                _appConfig.value = it
            }
        }
    }

    fun toggleThemeMode() {
        viewModelScope.launch {
            setConfigUseCase.toggleTheme()
        }
    }

    fun toggleCardStyle() {
        viewModelScope.launch {
            setConfigUseCase.toggleCardStyle()
        }
    }

    fun clearCache() {
        clearCacheJob = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                setConfigUseCase.clearCache()
            }
        }
    }

    fun updateLanguage(context: Context, language: Language) {
        viewModelScope.launch {
            setConfigUseCase.updateLanguage(language.code)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(language.code)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.code))
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        clearCacheJob?.cancel()
        super.onPause(owner)
    }
}