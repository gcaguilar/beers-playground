package com.gcaguilar.beer.search_beer.presentation

import androidx.navigation.navArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val SEARCH = "search"
const val SEARCH_ROUTE = "searchRoute"
private const val BEER_DETAIL = "beerDetail/{bid}"
const val FILTER = "filter"
private const val BID = "bid"

fun NavGraphBuilder.searchGraph(navHostController: NavHostController) {
    navigation(startDestination = SEARCH, route = SEARCH_ROUTE) {
        composable(route = SEARCH) {
            SearchScreen(navHostController)
        }
        composable(
            route = BEER_DETAIL,
            arguments = listOf(navArgument(BID) { defaultValue = "" })
        ) { backStackEntry ->
            BeerDetailScreen(bid = backStackEntry.arguments?.getInt("bid")!!)
        }
        composable(
            route = FILTER
        ) {
            FilterScreen(navHostController)
        }
    }
}