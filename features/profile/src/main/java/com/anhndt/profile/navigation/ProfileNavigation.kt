package com.anhndt.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anhndt.common.constants.Destination

fun NavGraphBuilder.profileScreen(
    navController: NavController
) {
    composable(route = Destination.Profile.path) {
        ProfileRoute(
            navController = navController,
        )
    }
}