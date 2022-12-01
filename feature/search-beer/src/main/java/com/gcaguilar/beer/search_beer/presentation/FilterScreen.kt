package com.gcaguilar.beer.search_beer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.gcaguilar.common_ui.ui.pagerTabIndicatorOffset
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

private val filtersNameList = listOf("General", "Style", "Country")

@Composable
fun FilterScreen(
    viewModel: FilterViewModel
) {
    val state = viewModel.state.collectAsState()
    val pagerState = rememberPagerState()

    Column {
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
                filtersNameList[0] -> FilterDialog(
                    hideDialog = { /*TODO*/ },
                    abv = state.value.abv,
                    ibu = state.value.ibu,
                    rate = state.value.rate
                )

                filtersNameList[1] -> LongListFilter(
                    itemsToFilterList = state.value.styleList,
                    allItemsSelected = state.value.isAllStylesSelected,
                    onCheckedChange = { id -> viewModel.onUpdateStyleState(id) },
                    onAllChecksChange = { viewModel.selectAllStyles() },
                    onTextChange = { name -> viewModel.onStyleSearchNameUpdated(name) },
                    onSearchClick = { viewModel.onSearchStyle() },
                    onClearClick = { viewModel.clearSearchCountry() },
                    onActionBackClick = { }
                )

                filtersNameList[2] -> LongListFilter(
                    itemsToFilterList = state.value.countryList,
                    allItemsSelected = state.value.isAllCountrySelected,
                    onCheckedChange = { id -> viewModel.onUpdateCountryState(id) },
                    onAllChecksChange = { viewModel.selectAllCountries() },
                    onTextChange = { name -> viewModel.onCountrySearchNameUpdated(name) },
                    onSearchClick = { viewModel.onSearchCountry() },
                    onClearClick = { viewModel.clearSearchCountry() },
                    onActionBackClick = {}
                )
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