package com.anhndt.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anhndt.designsystem.extensions.square
import com.anhndt.l10n.R
import com.anhndt.model.Language
import com.anhndt.profile.constants.SUPPORT_LANGUAGES
import com.anhndt.profile.ui.component.LanguageListTile
import com.anhndt.profile.ui.component.ListTile
import com.anhndt.profile.ui.component.SwitchListTile
import com.anhndt.profile.ui.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
) {
    val appConfigState = viewModel.appConfig.collectAsState()
    val buildVersionState = viewModel.buildVersion.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val currentLanguage: Language =
        SUPPORT_LANGUAGES.find { it.code == (appConfigState.value?.language ?: "en") }
            ?: SUPPORT_LANGUAGES[2]

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getBuildVersion(context)
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.square(96.dp),
                            imageVector = Icons.Default.FormatQuote,
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = stringResource(R.string.app_name)
                        )
                    }
                }

                ListTile(
                    leadingIcon = Icons.Default.AccountCircle,
                    titleText = stringResource(R.string.profile_mn_updateProfile),
                ) {

                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                // Theme mode
                SwitchListTile(
                    leadingIcon = Icons.Default.DesignServices,
                    titleText = stringResource(R.string.profile_mn_themeMode),
                    isChecked = appConfigState.value?.isDarkMode ?: true,
                    isCustomIcon = true,
                    selectedIcon = Icons.Filled.DarkMode,
                    unselectedIcon = Icons.Filled.LightMode,
                ) {
                    viewModel.toggleThemeMode()
                }

                // Card style
                SwitchListTile(
                    leadingIcon = Icons.Default.Style,
                    titleText = stringResource(R.string.profile_mn_quoteStyle),
                    subtitleText = stringResource(R.string.profile_mn_quoteStyleDescription),
                    isChecked = appConfigState.value?.isCardStyle ?: true,
                    isCustomIcon = true,
                    selectedIcon = Icons.Default.Style,
                ) {
                    viewModel.toggleCardStyle()
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                ListTile(
                    leadingIcon = Icons.Default.Translate,
                    titleText = stringResource(R.string.profile_mn_language),
                    subtitleText = currentLanguage.name
                ) {
                    showBottomSheet = true
                }

                ListTile(
                    leadingIcon = Icons.Default.Cached,
                    titleText = stringResource(R.string.profile_mn_clearData),
                    subtitleText = stringResource(R.string.profile_mn_clearDataDescription),
                ) {
                    showConfirmDialog = true
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                ListTile(
                    leadingIcon = Icons.Default.Share,
                    titleText = stringResource(R.string.profile_mn_share),
                ) {

                }
                ListTile(
                    leadingIcon = Icons.Default.PrivacyTip,
                    titleText = stringResource(R.string.profile_mn_privacyPolicy),
                ) {

                }
                ListTile(
                    leadingIcon = Icons.Default.Info,
                    titleText = stringResource(R.string.profile_mn_about),
                    subtitleText = stringResource(
                        R.string.profile_mn_version,
                        buildVersionState.value
                    ),
                ) {

                }
            }

            // Bottom Sheet
            if (showBottomSheet) {
                BottomSheetUI(
                    modifier = Modifier,
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState,
                    onUpdateLanguage = { language ->
                        showBottomSheet = false
                        viewModel.updateLanguage(context, language)
                    },
                    selectedLanguage = appConfigState.value?.language
                )
            }

            if (showConfirmDialog) {
                ConfirmClearCacheDialog(
                    onDismissRequest = {
                        showConfirmDialog = false
                    },
                    onConfirm = {
                        showConfirmDialog = false
                        viewModel.clearCache()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetUI(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    selectedLanguage: String? = null,
    onUpdateLanguage: (Language) -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            SUPPORT_LANGUAGES.forEach { item ->
                LanguageListTile(
                    language = item,
                    isSelected = item.code == (selectedLanguage ?: "en")
                ) {
                    onUpdateLanguage(item)
                }
            }

        }

    }
}

@Composable
fun ConfirmClearCacheDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(text = stringResource(R.string.profile_dl_clearCacheConfirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(text = stringResource(R.string.profile_dl_clearCacheCancel))
            }
        },
        title = { Text(text = stringResource(R.string.profile_dl_clearCacheTitle)) },
        text = { Text(text = stringResource(R.string.profile_dl_clearCacheTitleDescription)) }
    )
}

