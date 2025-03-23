package com.anhndt.profile.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ListTile(
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector = Icons.AutoMirrored.Filled.Article,
    titleText: String = "Title",
    subtitleText: String? = null,
    iconTitleSpacing: Dp = 32.dp,
    onClick: () -> Unit
) {
    Box(modifier = modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick.invoke()
                }
                .padding(horizontal = 32.dp, vertical = 16.dp)
        ) {
            val (icon, content) = createRefs()

            // Icon
            Icon(
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                imageVector = leadingIcon,
                contentDescription = "Icon"
            )

            Column(modifier = Modifier.constrainAs(content) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(icon.end, margin = iconTitleSpacing)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }) {
                // Title
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = titleText,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 20.sp
                    ),
                )

                // Subtitle
                if (!subtitleText.isNullOrEmpty())
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = subtitleText,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleSmall,
                    )
            }

        }
    }
}

@Preview
@Composable
fun MenuItemPreview() {
    ListTile(onClick = {})
}
