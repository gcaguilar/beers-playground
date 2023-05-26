package com.gcaguilar.authentication

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun AuthenticationScreen(
    authenticationViewModel: AuthenticationViewModel = hiltViewModel()
) {
    val intent: CustomTabsIntent = CustomTabsIntent.Builder().build()
    intent.launchUrl(LocalContext.current, Uri.parse(authenticationViewModel.getLoginUrl()))
}
