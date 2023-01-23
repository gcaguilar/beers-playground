package com.gcaguilar.beer.search_beer.presentation

import com.gcaguilar.beer.search_beer.domain.Beer
import com.gcaguilar.beer.search_beer.presentation.SearchViewModel.SearchState

data class SearchUIState(
    val beers: List<Beer> = emptyList(),
    val searchText: String = ""
)

fun SearchState.toUIState(): SearchUIState = SearchUIState(
    beers = searchResult?.beers ?: emptyList(),
    searchText = searchText
)