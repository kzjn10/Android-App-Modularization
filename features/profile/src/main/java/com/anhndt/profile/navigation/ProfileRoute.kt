package com.anhndt.profile.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anhndt.profile.ui.ProfileScreen
import com.anhndt.profile.ui.viewmodel.ProfileViewModel

@Composable
fun ProfileRoute(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    ProfileScreen(
        viewModel = viewModel,
    )

}