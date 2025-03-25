package com.anhndt.quote.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anhndt.domain.usecase.settings.GetConfigUseCase
import com.anhndt.model.AppConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getConfigUseCase: GetConfigUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    private val _appConfig = MutableStateFlow<AppConfig?>(null)
    val appConfig: StateFlow<AppConfig?> = _appConfig

    private var loadConfigJob: Job? = null

    init {
        loadConfig()
    }

    private fun loadConfig() {
        loadConfigJob?.cancel()
        loadConfigJob = viewModelScope.launch {
            getConfigUseCase.getAppConfig().collect {
                _appConfig.value = it
            }
        }

    }

    override fun onPause(owner: LifecycleOwner) {
        loadConfigJob?.cancel()
        super.onPause(owner)
    }
}