package com.anhndt.favorite.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.anhndt.designsystem.components.emptycard.EmptyCard
import com.anhndt.l10n.R

@Composable
fun FavoriteScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        EmptyCard(
            message = R.string.message_empty_favorite,
        )
    }
}