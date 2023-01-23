package com.gcaguilar.beer.search_beer.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection.Ltr
import androidx.hilt.navigation.compose.hiltViewModel
import com.gcaguilar.beer.search_beer.data.model.ItemToFilter
import com.gcaguilar.common_ui.ui.pagerTabIndicatorOffset
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

private val filtersNameList = listOf("General", "Style", "Country")

@Composable
fun FilterScreen(
    viewModel: FilterViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val pagerState = rememberPagerState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Filters") },
                navigationIcon = {
                    IconButton(onClick = { viewModel.onClickBack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Search")
                    }
                }
            )
        },
        content = { paddingValues ->
            FilterContent(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    start = paddingValues.calculateStartPadding(Ltr),
                    end = paddingValues.calculateEndPadding(Ltr)
                ),
                abv = state.value.abv,
                ibu = state.value.ibu,
                rate = state.value.rate,
                searchStyleName = state.value.searchStyleName,
                styleList = state.value.styleList,
                isAllStyleSelected = state.value.isAllStylesSelected,
                searchCountryName = state.value.searchCountryName,
                countryList = state.value.countryList,
                isAllCountrySelected = state.value.isAllCountrySelected,
                onUpdateStyleState = { id -> viewModel.onUpdateStyleState(id) },
                onSelectAllStyles = { viewModel.selectAllStyles(it) },
                onStyleSearchNameUpdated = { viewModel.onStyleSearchNameUpdated(it) },
                onSearchStyle = { viewModel.onSearchStyle() },
                onClearStyleCountry = { viewModel.clearSearchStyle() },
                onUpdateCountryState = { id -> viewModel.onUpdateCountryState(id) },
                onSelectAllCountries = { viewModel.selectAllCountries(it) },
                onCountrySearchNameUpdated = { viewModel.onCountrySearchNameUpdated(it) },
                onSearchCountry = { viewModel.onSearchCountry() },
                onClearSearchCountry = { viewModel.clearSearchCountry() },
                onIbuChange = { viewModel.onIbuChange(it) },
                onAbvChange = { viewModel.onAbvChange(it) },
                onRateChange = { viewModel.onRateChange(it) },
                pagerState = pagerState
            )
        }
    )
}


@Composable
fun FilterContent(
    modifier: Modifier,
    abv: Float,
    ibu: Float,
    rate: Int,
    searchStyleName: String,
    styleList: List<ItemToFilter>,
    isAllStyleSelected: Boolean,
    searchCountryName: String,
    countryList: List<ItemToFilter>,
    isAllCountrySelected: Boolean,
    onUpdateStyleState: (id: Int) -> Unit,
    onSelectAllStyles: (selected: Boolean) -> Unit,
    onStyleSearchNameUpdated: (name: String) -> Unit,
    onSearchStyle: () -> Unit,
    onClearStyleCountry: () -> Unit,
    onUpdateCountryState: (id: Int) -> Unit,
    onSelectAllCountries: (selected: Boolean) -> Unit,
    onCountrySearchNameUpdated: (name: String) -> Unit,
    onSearchCountry: () -> Unit,
    onClearSearchCountry: () -> Unit,
    onIbuChange: (Float) -> Unit,
    onAbvChange: (Float) -> Unit,
    onRateChange: (Int) -> Unit,
    pagerState: PagerState,
) {
    Column(modifier = modifier) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            filtersNameList.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title) },
                    selected = pagerState.currentPage == index,
                    onClick = {}
                )
            }
        }

        HorizontalPager(count = filtersNameList.size, state = pagerState) { page ->
            when (filtersNameList[page]) {
                filtersNameList[0] -> GenericFilterScreen(
                    abv = abv,
                    ibu = ibu,
                    rate = rate,
                    onAbvChange = { onAbvChange(it) },
                    onIbuChange = { onIbuChange(it) },
                    onRateChange = { onRateChange(it) }
                )

                filtersNameList[1] -> {
                    if (searchStyleName.isEmpty() && styleList.isEmpty()) {
                        LaunchedEffect(key1 = "Style") {
                            onSearchStyle()
                        }
                    }

                    LongListFilter(
                        searchItem = searchStyleName,
                        itemsToFilterList = styleList,
                        allItemsSelected = isAllStyleSelected,
                        onCheckedChange = { id -> onUpdateStyleState(id) },
                        onAllChecksChange = { onSelectAllStyles(it) },
                        onTextChange = { name -> onStyleSearchNameUpdated(name) },
                        onSearchClick = { onSearchStyle() },
                        onClearClick = { onClearStyleCountry() },
                    )
                }

                filtersNameList[2] -> {
                    if (searchCountryName.isEmpty() && countryList.isEmpty()) {
                        LaunchedEffect(key1 = "Country") {
                            onSearchCountry()
                        }
                    }

                    LongListFilter(
                        searchItem = searchCountryName,
                        itemsToFilterList = countryList,
                        allItemsSelected = isAllCountrySelected,
                        onCheckedChange = { id -> onUpdateCountryState(id) },
                        onAllChecksChange = { onSelectAllCountries(it) },
                        onTextChange = { name -> onCountrySearchNameUpdated(name) },
                        onSearchClick = { onSearchCountry() },
                        onClearClick = { onClearSearchCountry() },
                    )
                }
            }
        }

        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Save search")
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Clear")
            }
        }
    }
}