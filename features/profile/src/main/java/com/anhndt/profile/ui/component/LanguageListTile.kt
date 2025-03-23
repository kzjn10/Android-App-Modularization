package com.anhndt.profile.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.anhndt.model.Language

@Composable
fun LanguageListTile(
    modifier: Modifier = Modifier,
    language: Language,
    itemSpacing: Dp = 32.dp,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    Box(modifier = modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick.invoke()
                }
                .padding(horizontal = 32.dp, vertical = 12.dp)
        ) {
            val (flag, text, select) = createRefs()

            // Icon
            Text(
                modifier = Modifier.constrainAs(flag) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
                text = language.flag,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp
                ),
            )

            Text(
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(flag.end, margin = itemSpacing)
                        end.linkTo(select.start, margin = itemSpacing)
                        width = Dimension.fillToConstraints
                    }
                    .fillMaxWidth(),
                text = language.name,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp
                ),
            )

            Icon(
                modifier = Modifier
                    .constrainAs(select) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .then(if (isSelected) Modifier else Modifier.alpha(0f)),
                imageVector = Icons.Default.CheckCircle,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Icon"
            )


        }
    }
}
