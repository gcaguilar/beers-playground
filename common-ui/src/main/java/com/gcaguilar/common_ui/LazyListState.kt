package com.gcaguilar.common_ui

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

val LazyStaggeredGridState.isLastItemVisible: Boolean
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

val LazyStaggeredGridState.isFirstItemVisible: Boolean
    get() = firstVisibleItemIndex == 0

data class ScrollContext(
    val isTop: Boolean,
    val isBottom: Boolean,
)

@Composable
fun rememberScrollContext(listState: LazyStaggeredGridState): ScrollContext {
    val scrollContext by remember {
        derivedStateOf {
            ScrollContext(
                isTop = listState.isFirstItemVisible,
                isBottom = listState.isLastItemVisible
            )
        }
    }
    return scrollContext
}