package com.anhndt.designsystem.components.emptycard

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anhndt.designsystem.R
import com.anhndt.designsystem.extensions.square

@Composable
fun EmptyCard(
    modifier: Modifier = Modifier,
    cardSize: Pair<Dp, Dp> = Pair(350.dp, 500.dp),
    @StringRes message: Int,
    icon: ImageVector? = null
) {
    Card(
        modifier = modifier
            .size(cardSize.first, cardSize.second),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.swc_elevation)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    modifier = Modifier.square(128.dp),
                    imageVector = icon ?: Icons.Default.HeartBroken,
                    contentDescription = null
                )
                Text(text = stringResource(id = message), textAlign = TextAlign.Center)
            }
        }
    }
}