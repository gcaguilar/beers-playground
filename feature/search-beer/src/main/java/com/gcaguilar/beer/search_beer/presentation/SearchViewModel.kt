package com.gcaguilar.beer.search_beer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.beer.search_beer.domain.Beer
import com.gcaguilar.beer.search_beer.domain.SearchAndFilter
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
    private val searchBeer: SearchAndFilter,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private var nextPage: Int? = null

    data class SearchState(
        val searchText: String = "",
        val beerList: List<Beer> = emptyList()
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
                val result = searchBeer(
                    name = searchState.searchText,
                    offset = nextPage
                )
                searchState = searchState.copy(beerList = searchState.beerList.plus(result.beers))
                nextPage = result.nextPage
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
