package com.gcaguilar.beer.search_beer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.beer.search_beer.data.SearchRepository
import com.gcaguilar.beer.search_beer.domain.SearchResult
import com.gcaguilar.untappd.navigation.NavigationManager
import com.gcaguilar.untappd.navigation.SearchDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBeer: SearchRepository,
    private val navigationManager: NavigationManager
) : ViewModel() {
    data class SearchState(
        val searchText: String = "",
        val hint: String = "Beer name",
        val searchResult: SearchResult? = null
    )

    private var searchState = SearchState()
        set(value) {
            field = value
            _state.update { field.toUIState() }
        }


    private val _state = MutableStateFlow(SearchUIState())
    val state: StateFlow<SearchUIState> = _state

    fun clearSearchText() {
        searchState = searchState.copy(searchText = "")
    }

    fun updateTextSearch(text: String) {
        searchState = searchState.copy(searchText = text)
    }

    fun search() {
        if (state.value.searchText.isNotEmpty()) {
            viewModelScope.launch {
                searchBeer.searchBeer(
                    name = searchState.searchText,
                    offset = searchState.searchResult?.nextPage ?: 0
                ).collect { result ->
                    searchState = searchState.copy(searchResult = result)
                }
            }
        }
    }

    fun onClickedBeer(bid: Int) {
        navigationManager.navigate(SearchDirections.BeerDetailNavigation.beerDetail(bid))
    }

    fun onFilterClick() {
        navigationManager.navigate(SearchDirections.filters)
    }
}
