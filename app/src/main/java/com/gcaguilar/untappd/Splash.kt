package com.gcaguilar.untappd

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gcaguilar.authentication.AUTHORIZATION_ROUTE
import com.gcaguilar.beer.search_beer.presentation.SEARCH
import com.gcaguilar.beer.search_beer.presentation.SEARCH_ROUTE

@Composable
fun Splash(
    viewModel: SplashViewModel = hiltViewModel(),
    onLoadingFinished: (String) -> Unit,
) {
    val state = viewModel.state.collectAsState()

    state.value.event?.let {
        val direction = if (it == SplashViewModel.NavigationEvent.NavigateToMain) {
            SEARCH_ROUTE
        } else {
            AUTHORIZATION_ROUTE
        }
        onLoadingFinished(direction).also {
            viewModel.processNavigation()
        }
    }

    LaunchedEffect(key1 = "Initialize") {
        viewModel.isLoggedIn()
    }
}

const val SPLASH = "splash"
const val SPLASH_ROUTE = "splashRoute"

fun NavGraphBuilder.splashGraph(
    onLoadingFinished: (String) -> Unit,
) {
    navigation(
        startDestination = SPLASH,
        route = SPLASH_ROUTE,
    ) {
        composable(SPLASH) {
            Splash(onLoadingFinished = onLoadingFinished)
        }
    }
}