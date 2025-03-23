package com.anhndt.quotedetail.ui.view

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.anhndt.designsystem.R
import com.anhndt.designsystem.constants.AppConstants.SWC_ICON_ROTATE
import com.anhndt.designsystem.extensions.square
import com.anhndt.quotedetail.ui.viewmodel.QuoteDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QuoteDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: QuoteDetailViewModel,
    author: String?,
    quote: String?,
) {

    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            println("Icon button clicked!")
                        }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        modifier = Modifier,
                        onClick = {
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, quote)
                                type = "text/plain"
                            }
                            context.startActivity(
                                Intent.createChooser(
                                    shareIntent,
                                    context.getString(com.anhndt.l10n.R.string.message_share)
                                )
                            )
                        }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )

        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            val (icon, content, subtitle) = createRefs()
            Icon(
                modifier = Modifier
                    .constrainAs(icon) {
                        start.linkTo(content.start)
                        bottom.linkTo(content.top, margin = -(60.dp))
                    }
                    .square(dimensionResource(R.dimen.swc_icon_size))
                    .rotate(SWC_ICON_ROTATE),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                imageVector = Icons.Default.FormatQuote, contentDescription = null
            )

            Text(
                modifier = Modifier
                    .constrainAs(content) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(16.dp),
                text = quote ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
            )

            Text(
                modifier = Modifier
                    .constrainAs(subtitle) {
                        top.linkTo(content.bottom, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = author ?: "",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )

        }

    }
}