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

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val isLogged: IsLogged
) : ViewModel() {
    data class UIState(
        val state: Status = Status.Loading,
        val event: NavigationEvent? = null
    )

    sealed interface Status {
        object Loading : Status
        object Idle : Status
    }

    sealed interface NavigationEvent {
        object NavigateToMain : NavigationEvent
        object NavigateToLogin : NavigationEvent
    }

    private val _state: MutableStateFlow<UIState> = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state.asStateFlow()

    fun isLoggedIn() {
        viewModelScope.launch {
            val logged = isLogged()
            _state.update {
                state.value.copy(
                    state = Status.Idle,
                    event = if (logged) NavigationEvent.NavigateToMain else NavigationEvent.NavigateToLogin
                )
            }
        }
    }

    fun processNavigation() {
        _state.update {
            state.value.copy(
                event = null
            )
        }
    }
}
