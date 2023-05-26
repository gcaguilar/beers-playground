package com.gcaguilar.beer.search_beer.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.gcaguilar.beer.search_beer.domain.Beer
import com.gcaguilar.common_ui.rememberScrollContext
import com.gcaguilar.common_ui.theme.Dimen
import com.gcaguilar.common_ui.ui.DefaultTopBar
import com.gcaguilar.common_ui.ui.SearchAppBar

private const val BEER_DETAIL = "beerDetail/"

@Composable
fun SearchScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val (searchBarShown, showSearchBar) = remember { mutableStateOf(false) }
    val listState = rememberLazyStaggeredGridState()
    val scrollContext = rememberScrollContext(listState)
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            AnimatedVisibility(visible = !searchBarShown) {
                DefaultTopBar(
                    title = "BeerWiki"
                ) {
                    IconButton(onClick = { showSearchBar(true) }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(
                        onClick = { viewModel.onFilterClick() },
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter"
                        )
                    }
                }
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
                    onFilterClick = { viewModel.onFilterClick() }
                )
            }
        },
        content = { paddingValues ->
            Text(text = "Hello", style = MaterialTheme.typography.titleLarge)
            if (state.value.beerList.isNotEmpty()) {
                ResultList(
                    modifier = Modifier.padding(paddingValues),
                    state = listState,
                    beersResult = state.value.beerList,
                    onClick = { id ->
                        viewModel.onClickedBeer(id)
                    }
                )
                if (scrollContext.isBottom) {
                    viewModel.search()
                }
            }
        }
    )

    state.value.event?.let { navigationEvent ->
        val direction = when (navigationEvent) {
            SearchViewModel.NavigationEvent.NavigateToFilter -> FILTER
            is SearchViewModel.NavigationEvent.NavigateToItem -> "$BEER_DETAIL${navigationEvent.id}"
            else -> ""
        }
        navController.navigate(direction).also { viewModel.processNavigation() }
    }
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
        verticalItemSpacing = Dimen.medium.dp,
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
            .fillMaxWidth(),
        onClick = onClick
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
