package com.gcaguilar.beer.search_beer.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor() : ViewModel() {
    data class UIState(
        val searchCountryName: String = "",
        val styleList: List<ItemToFilter> = emptyList(),
        val searchStyleName: String = "",
        val isAllStylesSelected: Boolean = false,
        val countryList: List<ItemToFilter> = emptyList(),
        val isAllCountrySelected: Boolean = false,
        val abv: Double = 0.0,
        val ibu: Int = 0,
        val rate: Int = 0
    )

    private val _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state

    fun onCountrySearchNameUpdated(name: String) {

    }

    fun selectAllCountries() {

    }

    fun onUpdateCountryState(id: Int) {

    }

    fun selectAllStyles() {

    }

    fun onUpdateStyleState(id: Int) {

    }

    fun onStyleSearchNameUpdated(name: String) {

    }

    fun onSearchCountry() {

    }

    fun onSearchStyle() {

    }

    fun clearSearchStyle() {

    }

    fun clearSearchCountry() {

    }
}