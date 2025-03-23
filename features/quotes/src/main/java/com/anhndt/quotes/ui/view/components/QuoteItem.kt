package com.anhndt.quotes.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.constraintlayout.compose.ConstraintLayout
import com.anhndt.designsystem.extensions.square
import com.anhndt.model.Quote


@Composable
fun QuoteItem(modifier: Modifier = Modifier, quote: Quote, onNavigateToDetail: (Quote) -> Unit) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onNavigateToDetail(quote)
            }
            .padding(
                horizontal = dimensionResource(com.anhndt.designsystem.R.dimen.item_padding_horizontal),
                vertical = dimensionResource(com.anhndt.designsystem.R.dimen.item_padding_vertical),
            )
    ) {
        val (image, title, author) = createRefs()

        // Avatar
        Box(
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .square(dimensionResource(com.anhndt.designsystem.R.dimen.avatar_size))
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(dimensionResource(com.anhndt.designsystem.R.dimen.avatar_corner_radius))
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = quote.quote.substring(0, 2).toUpperCase(Locale.current).trim(),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }

        // Quote
        Text(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = androidx.constraintlayout.compose.Dimension.fillToConstraints
                }
                .padding(
                    start = dimensionResource(com.anhndt.designsystem.R.dimen.avatar_margin_end),
                    bottom = dimensionResource(com.anhndt.designsystem.R.dimen.line_spacing)
                ),
            text = quote.quote,
            style = MaterialTheme.typography.titleMedium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )

        // Author
        Text(
            modifier = Modifier
                .constrainAs(author) {
                    top.linkTo(title.bottom)
                    start.linkTo(image.end)
                }
                .padding(start = dimensionResource(com.anhndt.designsystem.R.dimen.avatar_margin_end)),
            text = quote.author
        )
    }

}