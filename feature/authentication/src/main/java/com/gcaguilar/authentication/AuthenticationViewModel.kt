package com.gcaguilar.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

private const val CLIENT_ID = "ClientId"
private const val REDIRECT_URL = "https://gcaguilar.eu/untappd"
private const val CLIENT_ID_PARAMETER = "?client_id="
private const val URL =
    "https://untappd.com/oauth/authenticate$CLIENT_ID_PARAMETER&response_type=code&redirect_url=$REDIRECT_URL"

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    @Named(CLIENT_ID) private val clientId: String,
    private val saveAccessToken: SaveAccessToken,
    private val isLogged: IsLogged
) : ViewModel() {
    private var currentUiState = UiState(
        isLoading = true,
        loginState = LoginState.Loading
    )

    init {
        viewModelScope.launch {
            currentUiState = if (isLogged()) {
                currentUiState.copy(isLoading = false, loginState = LoginState.Logged)
            } else {
                currentUiState.copy(
                    isLoading = false,
                    loginState = LoginState.NotLogged(
                        url = getLoginUrl()
                    )
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(currentUiState)
    val uiState: StateFlow<UiState> get() = _uiState

    private fun getLoginUrl() = URL.replace(CLIENT_ID_PARAMETER, CLIENT_ID_PARAMETER.plus(clientId))

    fun onCodeRetrieved(url: String?) {
        viewModelScope.launch {
            if (saveAccessToken(url, REDIRECT_URL)) {
                _uiState.value = _uiState.value.copy(isLoading = false, loginState = LoginState.Logged)
            }
        }

    }

    data class UiState(
        val isLoading: Boolean,
        val loginState: LoginState
    )

    sealed class LoginState {
        object Logged : LoginState()
        object Loading : LoginState()
        data class NotLogged(val url: String) : LoginState()
    }
}