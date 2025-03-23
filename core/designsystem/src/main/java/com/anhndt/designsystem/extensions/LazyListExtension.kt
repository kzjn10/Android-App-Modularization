package com.anhndt.designsystem.extensions

import androidx.compose.foundation.lazy.LazyListState

suspend fun LazyListState.scrollToTop(){
    animateScrollToItem(0)
}