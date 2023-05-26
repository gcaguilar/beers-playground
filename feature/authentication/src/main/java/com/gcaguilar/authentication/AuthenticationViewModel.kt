package com.gcaguilar.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

private const val CLIENT_ID = "ClientId"
private const val APP_URL = "AppUrl"
private const val CLIENT_ID_PARAMETER = "?client_id="
private const val APP_URL_PARAMETER = "&redirect_url="
private const val RESPONSE_TYPE_PARAMETER = "&response_type=code"
private const val URL = "https://untappd.com/oauth/authenticate/"

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    @Named(CLIENT_ID) private val clientId: String,
    @Named(APP_URL) private val appUrl: String,
) : ViewModel() {
    fun getLoginUrl() = URL
        .plus("$CLIENT_ID_PARAMETER$clientId")
        .plus(RESPONSE_TYPE_PARAMETER)
        .plus("$APP_URL_PARAMETER$appUrl")
}