package com.anhndt.domain.usecase.settings

import com.anhndt.model.AppConfig
import kotlinx.coroutines.flow.Flow

interface GetConfigUseCase {
    fun getAppConfig(): Flow<AppConfig>
}