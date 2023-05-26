package com.gcaguilar.beer.search_beer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.beer.search_beer.domain.Beer
import com.gcaguilar.beer.search_beer.domain.SearchAndFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBeer: SearchAndFilter,
) : ViewModel() {
    private var nextPage: Int? = null

    data class SearchState(
        val searchText: String = "",
        val beerList: List<Beer> = emptyList(),
        val event: NavigationEvent? = null
    )

    sealed interface NavigationEvent {
        data class NavigateToItem(val id: Int) : NavigationEvent
        object NavigateToFilter : NavigationEvent
    }


    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state

    fun clearSearchText() {
        _state.update {
            state.value.copy(
                searchText = ""
            )
        }
    }

    fun updateTextSearch(text: String) {
        _state.update {
            state.value.copy(
                searchText = text
            )
        }
    }

    fun search() {
        if (state.value.searchText.isNotEmpty()) {
            viewModelScope.launch {
                val result = searchBeer(
                    name = state.value.searchText,
                    offset = nextPage
                )
                _state.update {
                    state.value.copy(
                        beerList = it.beerList.plus(result.beers)
                    )
                }
                nextPage = result.nextPage
            }
        }
    }

    fun onClickedBeer(bid: Int) {
        _state.update {
            state.value.copy(
                event = NavigationEvent.NavigateToItem(bid)
            )
        }
    }

    fun processNavigation() {
        _state.update {
            state.value.copy(
                event = null
            )
        }
    }

    fun onFilterClick() {
        _state.update {
            state.value.copy(
                event = NavigationEvent.NavigateToFilter
            )
        }
    }
}
