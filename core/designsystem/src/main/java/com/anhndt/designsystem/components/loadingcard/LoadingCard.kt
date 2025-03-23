package com.anhndt.designsystem.components.loadingcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.anhndt.designsystem.R
import com.anhndt.designsystem.constants.AppConstants.CARD_RATIO
import com.anhndt.designsystem.extensions.shimmerBackground
import kotlin.math.min

@Composable
fun LoadingCard(modifier: Modifier = Modifier) {
    val localConfig = LocalConfiguration.current
    val height = min(localConfig.screenWidthDp, localConfig.screenHeightDp)
    val width = height * CARD_RATIO

    Card(
        modifier = Modifier
            .size(width.dp, height.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.swc_elevation)
        )
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .shimmerBackground()
        ) {

        }
    }

}