package com.gcaguilar.untappd

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gcaguilar.authentication.authorizationGraph
import com.gcaguilar.beer.search_beer.presentation.searchGraph
import com.gcaguilar.common_ui.theme.AppTheme

@Composable
fun MainScreen(
    navController: NavHostController,
    showBottomBar: Boolean,
    modifier: Modifier = Modifier
) {
    AppTheme {
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    HomeBottomNavigation(navController)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController,
                startDestination = SPLASH_ROUTE,
                modifier.padding(innerPadding)
            ) {
                searchGraph(navController)
                authorizationGraph()
                splashGraph(
                    onLoadingFinished = { direction ->
                        navController.navigate(direction)
                    }
                )
            }
        }
    }
}
