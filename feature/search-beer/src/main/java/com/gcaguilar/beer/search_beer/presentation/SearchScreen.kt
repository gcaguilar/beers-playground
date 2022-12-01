package com.gcaguilar.beer.search_beer.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gcaguilar.beer.search_beer.domain.Beer
import com.gcaguilar.common_ui.rememberScrollContext
import com.gcaguilar.common_ui.theme.Dimen
import com.gcaguilar.common_ui.ui.DefaultTopBar
import com.gcaguilar.common_ui.ui.SearchAppBar

private const val BUFFER = 2

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    bottomNavigation: @Composable () -> Unit
) {
    val state = viewModel.state.collectAsState()
    val (dialogOpen, showDialog) = remember { mutableStateOf(false) }
    val (searchBarShown, showSearchBar) = remember { mutableStateOf(false) }
    val listState = rememberLazyStaggeredGridState()
    val scrollContext = rememberScrollContext(listState)

    Scaffold(
        modifier = modifier,
        topBar = {
            AnimatedVisibility(visible = !searchBarShown) {
                DefaultTopBar(
                    title = "BeerWiki",
                    action = {
                        IconButton(onClick = { showSearchBar(true) }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        }
                        IconButton(
                            onClick = { showDialog(true) },
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList ,
                                contentDescription = "Filter"
                            )
                        }
                    }
                )
            }
            AnimatedVisibility(visible = searchBarShown) {
                SearchAppBar(
                    value = state.value.searchText,
                    label = "Brewery beer name",
                    filtered = false,
                    onClearClick = { viewModel.clearSearchText() },
                    onTextChange = { text -> viewModel.updateTextSearch(text) },
                    onFocusChanged = {},
                    onSearchClick = { viewModel.search() },
                    onActionBackClick = { showSearchBar(false) },
                    onFilterClick = { showDialog(true) }
                )
            }
        },
        content = { paddingValues ->
            if (state.value.beers.isNotEmpty()) {
                ResultList(
                    modifier = Modifier.padding(paddingValues),
                    state = listState,
                    beersResult = state.value.beers,
                    onClick = { id ->
                        viewModel.onClickedBeer(id)
                    }
                )
                if (scrollContext.isBottom) {
                    viewModel.search()
                }
            }
            if (dialogOpen) {
                FilterDialog(
                    hideDialog = { showDialog(false) },
                    abv = 0.0,
                    ibu = 0,
                    rate = 0
                )
            }
        },
        bottomBar = bottomNavigation
    )
}

@Composable
fun ResultList(
    beersResult: List<Beer>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyStaggeredGridState,
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.padding(Dimen.medium.dp),
        state = state,
        horizontalArrangement = Arrangement.spacedBy(Dimen.medium.dp),
        verticalArrangement = Arrangement.spacedBy(Dimen.medium.dp),
        columns = StaggeredGridCells.Adaptive(136.dp)
    ) {
        items(beersResult.size) { beer ->
            ResultItem(
                beer = beersResult[beer],
                onClick = { onClick(beersResult[beer].bid) },
            )
        }
    }
}

@Composable
fun ResultItem(
    beer: Beer,
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier = Modifier
            .clip(RoundedCornerShape(Dimen.small.dp))
            .clickable { onClick() }
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = beer.image,
            placeholder = painterResource(id = androidx.appcompat.R.drawable.abc_ic_menu_copy_mtrl_am_alpha),
            contentDescription = "Beer image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = Dimen.small.dp,
                        topEnd = Dimen.small.dp,
                        bottomEnd = Dimen.small.dp,
                        bottomStart = Dimen.small.dp,
                    )
                )
                .height(100.dp)
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(
                    top = Dimen.medium.dp,
                    start = Dimen.medium.dp,
                    end = Dimen.medium.dp,
                    bottom = Dimen.medium.dp
                )
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimen.small.dp),
        ) {
            Text(
                text = beer.name,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = beer.style,
                style = MaterialTheme.typography.bodyLarge
            )
            Row(horizontalArrangement = Arrangement.spacedBy(Dimen.xs.dp)) {
                Text(
                    text = "${beer.abv} - ${beer.ibu} IBU",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.Start)
            ) {
                Text(text = "Rate")
            }
        }
    }
}

@Preview
@Composable
fun SearchAppBarPreview() {
    SearchAppBar(
        value = "",
        label = "Search beer",
        filtered = false,
        onFocusChanged = {},
        onTextChange = {},
        onSearchClick = {},
        onActionBackClick = {},
        onFilterClick = {}
    )
}
