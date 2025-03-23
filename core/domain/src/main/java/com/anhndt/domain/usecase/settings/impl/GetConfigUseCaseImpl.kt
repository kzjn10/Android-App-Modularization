package com.anhndt.domain.usecase.settings.impl

import com.anhndt.domain.usecase.settings.GetConfigUseCase
import com.anhndt.local.repository.SettingLocalRepository
import com.anhndt.model.AppConfig
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConfigUseCaseImpl @Inject constructor(
    private val settingLocalRepository: SettingLocalRepository
) : GetConfigUseCase {
    override fun getAppConfig(): Flow<AppConfig> = settingLocalRepository.getAppConfig()

}