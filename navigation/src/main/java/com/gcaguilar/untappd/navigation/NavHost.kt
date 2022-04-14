package com.gcaguilar.untappd.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gcaguilar.untappd.presentation.SearchScreen

const val SEARCH = "search"
const val START_DESTINATION = SEARCH

@Composable
fun Navigator(navController: NavHostController) {
    NavHost(navController = navController, startDestination = START_DESTINATION) {
        composable(SEARCH) {
            SearchScreen(navController = navController, searchViewModel = hiltViewModel())
        }
    }
}
