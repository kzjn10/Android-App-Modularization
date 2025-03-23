package com.anhndt.quote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anhndt.designsystem.theme.QuoteAppTheme
import com.anhndt.quote.ui.AdaptiveApp
import com.anhndt.quote.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContent {
            val viewModel: MainViewModel = hiltViewModel()
            val appConfig by viewModel.appConfig.collectAsStateWithLifecycle()

            QuoteAppTheme(darkTheme = appConfig?.isDarkMode ?: true) {
                AdaptiveApp(
                    viewModel = viewModel,
                )
            }
        }
    }

}

