package com.gcaguilar.untappd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.authentication.IsLogged
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UIState(val isLoading: Boolean = true)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isLogged: IsLogged,
) : ViewModel() {
    private val _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state.asStateFlow()

    fun isLoggedIn() {
        viewModelScope.launch {
            if (isLogged()) {
                // navigationManager.navigate(SearchDirections.root)
            } else {
                // navigationManager.navigate(AuthenticationDirections.root)
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}
