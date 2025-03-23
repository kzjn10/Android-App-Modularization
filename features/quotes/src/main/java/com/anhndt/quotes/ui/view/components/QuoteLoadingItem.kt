package com.anhndt.quotes.ui.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.anhndt.designsystem.extensions.shimmerBackground
import com.anhndt.designsystem.extensions.square

@Composable
fun QuoteLoadingItem(modifier: Modifier = Modifier) {
    // Use constraint layout to align the items
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(com.anhndt.designsystem.R.dimen.item_padding_horizontal),
                vertical = dimensionResource(com.anhndt.designsystem.R.dimen.item_padding_vertical),
            )
    ) {
        val (image, title1, title2, author) = createRefs()

        Box(
            modifier = Modifier
                .square(dimensionResource(com.anhndt.designsystem.R.dimen.avatar_size))
                .shimmerBackground(shape = RoundedCornerShape(dimensionResource(com.anhndt.designsystem.R.dimen.avatar_corner_radius)))
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
        )

        Box(
            modifier = Modifier
                .padding(
                    start = dimensionResource(com.anhndt.designsystem.R.dimen.avatar_margin_end),
                    bottom = dimensionResource(com.anhndt.designsystem.R.dimen.shimmer_line_spacing)
                )
                .shimmerBackground(shape = RoundedCornerShape(dimensionResource(com.anhndt.designsystem.R.dimen.avatar_corner_radius)))
                .height(dimensionResource(com.anhndt.designsystem.R.dimen.shimmer_title_height))
                .constrainAs(title1) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = androidx.constraintlayout.compose.Dimension.fillToConstraints
                }
        )

        Box(
            modifier = Modifier
                .padding(
                    start = dimensionResource(com.anhndt.designsystem.R.dimen.avatar_margin_end),
                    bottom = dimensionResource(com.anhndt.designsystem.R.dimen.shimmer_line_spacing)
                )
                .shimmerBackground(shape = RoundedCornerShape(dimensionResource(com.anhndt.designsystem.R.dimen.avatar_corner_radius)))
                .height(dimensionResource(com.anhndt.designsystem.R.dimen.shimmer_title_height))
                .constrainAs(title2) {
                    top.linkTo(title1.bottom)
                    start.linkTo(image.end)
                    end.linkTo(parent.end)
                    width = androidx.constraintlayout.compose.Dimension.fillToConstraints
                }
        )

        Box(
            modifier = Modifier
                .padding(
                    start = dimensionResource(com.anhndt.designsystem.R.dimen.avatar_margin_end)
                )
                .shimmerBackground(shape = RoundedCornerShape(dimensionResource(com.anhndt.designsystem.R.dimen.avatar_corner_radius)))
                .height(dimensionResource(com.anhndt.designsystem.R.dimen.shimmer_desc_height))
                .width(dimensionResource(com.anhndt.designsystem.R.dimen.shimmer_desc_width))
                .constrainAs(author) {
                    top.linkTo(title2.bottom)
                    start.linkTo(image.end)
                })

    }
}