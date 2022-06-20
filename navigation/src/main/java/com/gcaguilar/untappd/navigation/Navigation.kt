package com.gcaguilar.untappd.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    AppNavigation(
        modifier = modifier,
        navController = navController,
    )
}