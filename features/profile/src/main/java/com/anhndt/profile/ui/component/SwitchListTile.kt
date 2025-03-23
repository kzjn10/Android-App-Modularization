package com.anhndt.profile.ui.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun SwitchListTile(
    modifier: Modifier = Modifier,
    titleText: String = "Theme mode",
    subtitleText: String? = null,
    iconTitleSpacing: Dp = 32.dp,
    isChecked: Boolean = false,
    isCustomIcon: Boolean = false,
    leadingIcon: ImageVector = Icons.Filled.Dashboard,
    selectedIcon: ImageVector = Icons.Filled.DarkMode,
    unselectedIcon: ImageVector? = null,
    onClick: (Boolean) -> Unit,
) {

    Log.d("TAG", "SwitchListTile: $isChecked")
    Box(modifier = modifier) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick.invoke(!isChecked)
                }
                .padding(horizontal = 32.dp, vertical = 12.dp)
        ) {
            val (icon, content, switch) = createRefs()

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
                end.linkTo(switch.start)
                width = androidx.constraintlayout.compose.Dimension.fillToConstraints
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

            Switch(
                modifier = Modifier.constrainAs(switch) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)

                },
                checked = isChecked,
                onCheckedChange = onClick,
                thumbContent = if (isCustomIcon) {
                    {
                        (if (isChecked) selectedIcon else unselectedIcon)?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                            )
                        }
                    }
                } else {
                    null
                }
            )


        }
    }
}
