package com.anhndt.designsystem.components.swipablecard

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.anhndt.designsystem.components.quotecard.QuoteCard
import com.anhndt.designsystem.constants.AppConstants.COERCE_FROM_ANGLE
import com.anhndt.designsystem.constants.AppConstants.COERCE_TO_ANGLE
import com.anhndt.designsystem.constants.AppConstants.DISTANT_TO_SWIPE_COMPLETE
import com.anhndt.designsystem.constants.AppConstants.DIS_SWIPE_LEFT_THRESHOLD
import com.anhndt.designsystem.constants.AppConstants.DIS_SWIPE_RIGHT_THRESHOLD
import com.anhndt.designsystem.constants.AppConstants.ROTATION_ANGLE
import com.anhndt.model.Quote
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun SwipeableCard(
    modifier: Modifier = Modifier,
    item: Quote,
    onSwipeLeft: () -> Unit,
    onSwipeRight: () -> Unit,
    isFavorite: Boolean = false,
    cardSize: Pair<Dp, Dp> = Pair(350.dp, 500.dp),
    onToggleFavorite: (Boolean) -> Unit = {}, index: Int
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var rotation by remember { mutableFloatStateOf(0f) }
    val animatableOffsetX = remember { Animatable(0f) }
    val swipeThreshold = DISTANT_TO_SWIPE_COMPLETE
    val scope = rememberCoroutineScope() // For manual launches

    Box(
        modifier = modifier
            .offset { IntOffset(animatableOffsetX.value.toInt(), 0) }
            .rotate(rotation)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        offsetX += dragAmount.x
                        rotation = (offsetX / ROTATION_ANGLE).coerceIn(
                            COERCE_FROM_ANGLE,
                            COERCE_TO_ANGLE
                        ) // Rotate based on drag
                        scope.launch {
                            animatableOffsetX.snapTo(offsetX)
                        }

                    },
                    onDragEnd = {
                        if (abs(offsetX) > swipeThreshold) {
                            // Swipe completed
                            val target =
                                if (offsetX > 0) DIS_SWIPE_RIGHT_THRESHOLD else DIS_SWIPE_LEFT_THRESHOLD
                            scope.launch {
                                animatableOffsetX.animateTo(
                                    targetValue = target,
                                    animationSpec = tween(durationMillis = 300)
                                )
                                if (offsetX > 0) onSwipeRight() else onSwipeLeft()
                                // Reset for next card
                                offsetX = 0f
                                rotation = 0f
                                animatableOffsetX.snapTo(0f)
                            }
                        } else {
                            // Return to center
                            scope.launch {
                                animatableOffsetX.animateTo(
                                    targetValue = 0f,
                                    animationSpec = tween(durationMillis = 200)
                                )
                                offsetX = 0f
                                rotation = 0f
                            }
                        }
                    }
                )
            }
    ) {
        QuoteCard(onToggleFavorite, isFavorite, item, cardSize)
    }

    LaunchedEffect(offsetX) {
        animatableOffsetX.animateTo(
            targetValue = offsetX,
            animationSpec = tween(durationMillis = 50)
        )
    }
}
