package com.gcaguilar.authentication

import android.graphics.Bitmap
import android.util.Log
import android.util.Log.d
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun AuthenticationScreen(
    modifier: Modifier,
    onUserAuthenticated: () -> Unit,
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {
    val uiState = authenticationViewModel.uiState.collectAsState()

    if (uiState.value.loginState is AuthenticationViewModel.LoginState.NotLogged) {
        val url = (uiState.value.loginState as AuthenticationViewModel.LoginState.NotLogged).url
        LoginComponent(
            url = url,
            onCodeRetrieved = { code: String? -> authenticationViewModel.onCodeRetrieved(code) })
    } else {
        onUserAuthenticated()
    }
}

@Composable
fun LoginComponent(url: String, onCodeRetrieved: (String?) -> Unit) {
    AndroidView(
        factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        onCodeRetrieved(url)
                    }
                }
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        })
}