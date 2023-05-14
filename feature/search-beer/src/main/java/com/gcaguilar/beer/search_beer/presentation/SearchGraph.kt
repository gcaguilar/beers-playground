package com.gcaguilar.beer.search_beer.presentation

import androidx.navigation.navArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val SEARCH = "search"
const val SEARCH_ROUTE = "searchRoute"
const val BEER_DETAIL = "beerDetail?bid={bid}"
const val FILTER = "filter"
private const val BID = "filter"

fun NavGraphBuilder.searchGraph(navController: NavHostController) {
    navigation(
        startDestination = SEARCH,
        route = SEARCH_ROUTE,
    ) {
        composable(
            route = SEARCH
        ) {
            SearchScreen(navController = navController)
        }
        composable(
            route = BEER_DETAIL,
            arguments = listOf(navArgument(BID) { defaultValue = "" })
        ) { backStackEntry ->
            BeerDetailScreen(bid = backStackEntry.arguments?.getInt("bid")!!)
        }
        composable(
            route = FILTER
        ) { backStackEntry ->
            FilterScreen()
        }
    }
}