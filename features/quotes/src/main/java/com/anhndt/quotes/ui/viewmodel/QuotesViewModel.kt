package com.anhndt.quotes.ui.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anhndt.domain.usecase.quote.GetQuotesUseCase
import com.anhndt.domain.usecase.settings.GetConfigUseCase
import com.anhndt.domain.usecase.settings.SetConfigUseCase
import com.anhndt.model.AppConfig
import com.anhndt.quotes.ui.state.QuoteListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getConfigUseCase: GetConfigUseCase,
    private val setConfigUseCase: SetConfigUseCase,
) : ViewModel(), DefaultLifecycleObserver {

    private val _uiState = MutableStateFlow<QuoteListState>(QuoteListState.Loading)
    val uiState: StateFlow<QuoteListState> = _uiState

    private val _currentCardIndex = MutableStateFlow(10)
    val currentCardIndex: StateFlow<Int> = _currentCardIndex

    private val _appConfig = MutableStateFlow<AppConfig?>(null)
    val appConfig: StateFlow<AppConfig?> = _appConfig

    private var getQuotesJob: Job? = null

    init {
        loadAppConfig()
        getQuotes(false)
    }

    private fun loadAppConfig() {
        viewModelScope.launch {
            getConfigUseCase.getAppConfig().collect {
                _appConfig.value = it
            }
        }
    }

    fun getQuotes(isForceRefresh: Boolean = false) {
        getQuotesJob?.cancel()
        getQuotesJob = viewModelScope.launch {
            _uiState.value = QuoteListState.Loading
            val result = withContext(Dispatchers.IO) {
                getQuotesUseCase.getQuotes(isForceRefresh)
            }
            result.fold(
                onSuccess = {
                    _currentCardIndex.value = Random.nextInt(0, it?.size ?: 0)
                    _uiState.value = QuoteListState.Success(it ?: emptyList())
                },
                onFailure = {
                    _uiState.value = QuoteListState.Error(it.message ?: "Something went wrong")
                }
            )
        }
    }

    fun onSwipeCard(isRight: Boolean = false) {
        // Not handle left or right
        _currentCardIndex.value -= 1
    }

    fun toggleUI() {
        viewModelScope.launch {
            setConfigUseCase.toggleCardStyle()
        }
    }


    override fun onPause(owner: LifecycleOwner) {
        getQuotesJob?.cancel()
        super.onPause(owner)
    }
}