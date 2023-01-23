package com.gcaguilar.beer.search_beer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.beer.search_beer.data.SearchRepository
import com.gcaguilar.beer.search_beer.data.model.ItemToFilter
import com.gcaguilar.untappd.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val searchRepository: SearchRepository
) : ViewModel() {
    data class UIState(
        val searchCountryName: String = "",
        val styleList: List<ItemToFilter> = emptyList(),
        val searchStyleName: String = "",
        val isAllStylesSelected: Boolean = false,
        val countryList: List<ItemToFilter> = emptyList(),
        val isAllCountrySelected: Boolean = false,
        val abv: Float = 0F,
        val ibu: Float = 0F,
        val rate: Int = 0
    )

    private val _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state

    fun onCountrySearchNameUpdated(name: String) {
        _state.update {
            state.value.copy(
                searchCountryName = name
            )
        }
    }

    fun selectAllCountries(selected: Boolean) {
        _state.update {
            state.value.copy(
                isAllCountrySelected = selected
            )
        }
        viewModelScope.launch {
            searchRepository.setAllCountriesChecked(state.value.isAllCountrySelected)
        }
    }

    fun onUpdateCountryState(id: Int) {
        viewModelScope.launch {
            searchRepository.setCountryChecked(
                !state.value.countryList.first { country -> country.id == id }.checked,
                id
            )
        }
    }

    fun selectAllStyles(selected: Boolean) {
        _state.update {
            state.value.copy(
                isAllStylesSelected = selected
            )
        }
        viewModelScope.launch {
            searchRepository.setAllStyleChecked(state.value.isAllStylesSelected)
        }
    }

    fun onUpdateStyleState(id: Int) {
        viewModelScope.launch {
            searchRepository.setCountryChecked(
                !state.value.styleList.first { style -> style.id == id }.checked,
                id
            )
        }
    }

    fun onStyleSearchNameUpdated(name: String) {
        _state.update {
            state.value.copy(
                searchStyleName = name
            )
        }
    }

    fun onSearchCountry() {
        viewModelScope.launch {
            searchRepository.getCountries(state.value.searchCountryName).collect { countries ->
                _state.update {
                    _state.value.copy(
                        countryList = countries
                    )
                }
            }
        }
    }

    fun onSearchStyle() {
        viewModelScope.launch {
            searchRepository.getStyles(state.value.searchStyleName).collect { styles ->
                _state.update {
                    state.value.copy(
                        styleList = styles
                    )
                }
            }
        }
    }

    fun clearSearchStyle() {
        _state.update {
            state.value.copy(
                searchStyleName = ""
            )
        }
    }

    fun clearSearchCountry() {
        _state.update {
            state.value.copy(
                searchCountryName = ""
            )
        }
    }

    fun onClickBack() {

    }

    fun onIbuChange(ibu: Float) {
        _state.update {
            state.value.copy(
                ibu = ibu
            )
        }
    }

    fun onAbvChange(abv: Float) {
        _state.update {
            state.value.copy(
                abv = abv
            )
        }
    }

    fun onRateChange(rate: Int) {
        _state.update {
            state.value.copy(
                rate = rate
            )
        }
    }
}