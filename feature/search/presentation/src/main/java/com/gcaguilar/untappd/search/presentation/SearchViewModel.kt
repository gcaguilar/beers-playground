package com.gcaguilar.untappd.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.untappd.domain.Beer
import com.gcaguilar.untappd.domain.SearchBeer
import com.gcaguilar.untappd.navigation.NavigationManager
import com.gcaguilar.untappd.navigation.SearchDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UIState(
    val searchText: String = "",
    val hint: String = "Beer name",
    val beers: List<Beer> = emptyList(),
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBeer: SearchBeer,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state

    fun clearSearchText() = _state.update { state -> state.copy(searchText = "") }

    fun updateTextSearch(text: String) = _state.update { state -> state.copy(searchText = text) }

    fun search() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { state -> state.copy(beers = searchBeer(_state.value.searchText)) }
        }
    }

    fun onClickedBeer(bid: Int) {
        navigationManager.navigate(SearchDirections.BeerDetailNavigation.beerDetail(bid))
    }
}
