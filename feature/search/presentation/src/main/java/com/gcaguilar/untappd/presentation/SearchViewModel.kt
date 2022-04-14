package com.gcaguilar.untappd.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.untappd.domain.Beer
import com.gcaguilar.untappd.domain.SearchBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchBeer: SearchBeer) : ViewModel() {
    private val initialUIModel = UIModel(
        searchText = "",
        hint = "Beer name",
        beers = emptyList()
    )

    private val _state = MutableStateFlow(initialUIModel)
    val state: StateFlow<UIModel> = _state

    fun updateTextSearch(text: String) {
        _state.value = initialUIModel.copy(
            searchText = text
        )
    }

    fun search() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = initialUIModel.copy(
                beers = searchBeer(_state.value.searchText)
            )
        }
    }

    data class UIModel(
        val searchText: String,
        val hint: String,
        val beers: List<Beer>
    )
}
