package com.gcaguilar.untappd

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.authentication.IsLogged
import com.gcaguilar.authentication.SaveAccessToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ACCESS_TOKEN = "accessToken"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveAccessToken: SaveAccessToken
) : ViewModel() {

    fun saveToken(it: Uri) {
        saveAccessToken(it.getQueryParameter(ACCESS_TOKEN) ?: "")
    }
}
