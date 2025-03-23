package com.anhndt.quote.constants

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.anhndt.common.constants.Destination
import com.anhndt.l10n.R

enum class AppDestinations(
    @StringRes val label: Int,
    val icon: ImageVector,
    val route: String,
) {
    HOME(R.string.mn_home, Icons.Default.Home, Destination.Quotes.path),
    FAVORITES(R.string.mn_favorite, Icons.Default.Favorite, Destination.Favorite.path),
    PROFILE(R.string.mn_profile, Icons.Default.AccountCircle, Destination.Profile.path),
}