package com.anhndt.designsystem.components.quotecard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.anhndt.designsystem.R
import com.anhndt.designsystem.constants.AppConstants.SWC_ICON_ROTATE
import com.anhndt.designsystem.extensions.square
import com.anhndt.model.Quote

@Composable
fun QuoteCard(
    onToggleFavorite: (Boolean) -> Unit,
    isFavorite: Boolean,
    item: Quote,
    cardSize: Pair<Dp, Dp> = Pair(350.dp, 500.dp),
) {
    Card(
        modifier = Modifier
            .size(cardSize.first, cardSize.second),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.swc_elevation)
        )
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.swc_padding))
                .fillMaxSize(),
        ) {
            val (favorite, icon, quote, author) = createRefs()

            IconButton(modifier = Modifier.constrainAs(favorite) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }, onClick = {
                onToggleFavorite(!isFavorite)
            }) {
                Icon(
                    imageVector = if (!isFavorite) Icons.Outlined.Favorite else Icons.Default.Favorite,
                    contentDescription = null
                )
            }

            Icon(
                modifier = Modifier
                    .constrainAs(icon) {
                        start.linkTo(quote.start)
                        bottom.linkTo(quote.top, margin = -(60.dp))
                    }
                    .square(dimensionResource(R.dimen.swc_icon_size))
                    .rotate(SWC_ICON_ROTATE),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                imageVector = Icons.Default.FormatQuote, contentDescription = null
            )

            // Quote
            Text(
                modifier = Modifier.constrainAs(quote) {
                    bottom.linkTo(author.top, margin = 8.dp)
                    start.linkTo(icon.start, margin = 16.dp)
                    end.linkTo(favorite.start)
                },
                text = item.quote,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineMedium
            )

            // Author
            Text(
                modifier = Modifier.constrainAs(author) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(quote.start)
                },
                text = item.author,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}